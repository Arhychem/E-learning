package Backend.Dto;

import Backend.entities.Lecon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class LeconDto {
    private String leconId;
    private String leconName;
    private String chapitreId;

    public static LeconDto map(Lecon lecon) {
        return  LeconDto.builder()
                .leconId(lecon.getLeconId())
                .leconName(lecon.getLeconName())
                .chapitreId(lecon.getChapitre().getChapitreId())
                .build();
    }
}
