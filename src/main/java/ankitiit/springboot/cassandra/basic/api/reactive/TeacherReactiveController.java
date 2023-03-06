package ankitiit.springboot.cassandra.basic.api.reactive;

import ankitiit.springboot.cassandra.basic.dto.Teacher;
import ankitiit.springboot.cassandra.basic.repository.reactive.TeacherReactiveRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.ReactiveCassandraBatchOperations;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reactive")
public class TeacherReactiveController {

     @Autowired
     TeacherReactiveRepository teacherRepository;

     @Autowired
    ReactiveCassandraTemplate reactiveCassandraTemplate;
     ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value="/bulkInsertAsync")
    public void bulkInsertAsync() throws Exception {
        List<Teacher> teacherList = mapper.readValue(new File("E:\\ProfessionalData\\SpringBootProjects\\JsonData\\teacher643k.json"), new TypeReference<List<Teacher>>(){});

       // teacherList = teacherList.stream().limit(10000).collect(Collectors.toList());
        Instant startTime = Instant.now();
       // Flux<Teacher> teacherFlux = teacherRepository.saveAll(teacherList);
        //teacherFlux.subscribe();
        //teacherRepository.saveAllBatch(teacherList).subscribe();
        /* Flux.fromIterable(teacherList)
                .buffer(100) // maximum number of entities to insert in one batch
                .map(teacherRepository::saveAll)
                .blockLast();*/
        List<List<Teacher>> batches = Lists.partition(teacherList, 700);
        for (List<Teacher> batch : batches) {
            ReactiveCassandraBatchOperations batchOps = reactiveCassandraTemplate.batchOps();
            for (Teacher teacher : batch) {
                batchOps.insert(teacher);
            }
            batchOps.execute().subscribe();
        }
        /*ReactiveCassandraBatchOperations batchOps = reactiveCassandraTemplate.batchOps();
        Flux.fromIterable(teacherList)
                .buffer(250)
                .flatMap( (List<Teacher>) batch -> batchOps.insert(batch))
                .then();*/

        Instant endTime = Instant.now();
        // Calculate the elapsed time in milliseconds
        long elapsedTimeMs = Duration.between(startTime, endTime).toMillis();
        System.out.println("TimeTaken" + elapsedTimeMs/1000);
    }


    @GetMapping(value="/bulkInsertAsyncreactive")
    public void bulkInserrt() throws Exception {
        List<Teacher> teacherList = mapper.readValue(new File("E:\\ProfessionalData\\SpringBootProjects\\JsonData\\teacher643k.json"), new TypeReference<List<Teacher>>(){});

        // teacherList = teacherList.stream().limit(10000).collect(Collectors.toList());
        Instant startTime = Instant.now();
        Flux<Teacher> teacherFlux = teacherRepository.saveAll(teacherList);
        teacherFlux.subscribe();
        Instant endTime = Instant.now();
        // Calculate the elapsed time in milliseconds
        long elapsedTimeMs = Duration.between(startTime, endTime).toMillis();
        System.out.println("TimeTaken" + elapsedTimeMs/1000);
    }

    @GetMapping(value="/getAllTeachers")
    public Flux<Teacher> getAllTeachers() throws Exception {
        Flux<Teacher> all = teacherRepository.findAll();
        return all.take(100);
    }

}
