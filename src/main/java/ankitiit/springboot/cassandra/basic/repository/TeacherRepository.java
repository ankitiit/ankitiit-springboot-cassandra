package ankitiit.springboot.cassandra.basic.repository;

import ankitiit.springboot.cassandra.basic.dto.Teacher;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface TeacherRepository extends CassandraRepository<Teacher, Integer> {
}
