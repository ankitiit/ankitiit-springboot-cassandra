package ankitiit.springboot.cassandra.basic.api;

import ankitiit.springboot.cassandra.basic.dto.Teacher;
import ankitiit.springboot.cassandra.basic.repository.TeacherRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.print.Book;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    TeacherRepository teacherRepository;

     @Autowired
     CqlSession session;

     @Autowired
     AsyncCassandraTemplate asyncTemplate;

     @Autowired
    CassandraTemplate cassandraTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    ExecutorService executor = Executors.newFixedThreadPool(10);

    @GetMapping(value="/bulkInsert")
    public void bulkInsert() throws Exception{
        List<Teacher> teacherList = objectMapper.readValue(new File("E:\\ProfessionalData\\SpringBootProjects\\JsonData\\teacher643k.json"), new TypeReference<List<Teacher>>(){});
        //teacherList = teacherList.stream().limit(100).collect(Collectors.toList());
        //teacherList = teacherList.stream().limit(200).collect(Collectors.toList());
        Instant startTime = Instant.now();
        List<List<Teacher>> batches = Lists.partition(teacherList, 200);


        for (List<Teacher> batch : batches) {
            CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
            for (Teacher teacher : batch) {
                batchOps.insert(teacher);
            }
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                // Your method logic goes here
                batchOps.execute();
                return null; // CompletableFuture requires a return value, so we return null here
            },executor);

          //  batchOps.execute();             // bllocking way
        }
        //teacherRepository.saveAll(teacherList);
        Instant endTime = Instant.now();
        // Calculate the elapsed time in milliseconds
        long elapsedTimeMs = Duration.between(startTime, endTime).toMillis();
        System.out.println("TimeTaken :" + elapsedTimeMs/1000);

    }

    @GetMapping(value="/bulkInsertAsync")
    public void bulkInsertAsync() throws Exception{
        List<Teacher> teacherList = objectMapper.readValue(new File("E:\\ProfessionalData\\SpringBootProjects\\JsonData\\teacher643k.json"), new TypeReference<List<Teacher>>(){});
        //teacherList = teacherList.stream().limit(100).collect(Collectors.toList());

        final var countDownLatch = new CountDownLatch(1);

        // var asyncTemplate = new AsyncCassandraTemplate(session);
        //asyncTemplate.insert(teacherList);
        teacherList.forEach(k ->{
            asyncTemplate.insert(k);
        });
        Thread.sleep(60000);
        System.out.println("Completed");
    }

    @GetMapping(value="/teacher/{id}")
    public Optional<Teacher> bulkInsert(@PathVariable Integer id) throws Exception {
        logger.info("Fetching teacher details for teacher id {}",id);
        return teacherRepository.findById(id);
    }


    @GetMapping(value="/teachers")
    public List<Teacher> getAllTeachers() throws Exception {
        logger.info("Fetching ALL teachers");
        // return teacherRepository.findAll().stream().limit(100).collect(Collectors.toList());
        List<Teacher> teacherList = objectMapper.readValue(new File("E:\\ProfessionalData\\SpringBootProjects\\JsonData\\teacher643k.json"), new TypeReference<List<Teacher>>(){});
        return teacherList.stream().limit(100).collect(Collectors.toList());
    }

}
