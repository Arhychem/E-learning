package Backend.wrapper;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class UserWrapper {
    private String id;
    private String name;
    private String email;
    private String contactNumber;
    private String status;
}
