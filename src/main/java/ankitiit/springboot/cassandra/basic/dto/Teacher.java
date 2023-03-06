package ankitiit.springboot.cassandra.basic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @PrimaryKey
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String highestQualification;
    private String subjects;
    private int experience;
    private String contactNumber;
    private String description;
    private String lastName;
}
