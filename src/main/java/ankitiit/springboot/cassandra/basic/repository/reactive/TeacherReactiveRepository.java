package ankitiit.springboot.cassandra.basic.repository.reactive;

import ankitiit.springboot.cassandra.basic.dto.Teacher;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Book;
import java.util.List;

public interface TeacherReactiveRepository  extends ReactiveCassandraRepository<Teacher,Integer> {

  /*  default Mono<Void> saveAllBatch(List<Teacher> teachers) {
        return Flux.fromIterable(teachers)
                .buffer(100) // maximum number of entities to insert in one batch
                .flatMap(this::saveAll)
                .then();
    }*/

}
