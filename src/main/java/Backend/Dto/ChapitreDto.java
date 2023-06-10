package Backend.Dto;

import Backend.entities.Chapitre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ChapitreDto {

    private String chapitreId;
    private String chapitreName;
    private String matiereId;
    private List<LeconDto> leconDtoList = new ArrayList<>();

    public static ChapitreDto map(Chapitre chapitre) {
        return  ChapitreDto.builder()
                .chapitreId(chapitre.getChapitreId())
                .chapitreName(chapitre.getChapitreName())
                .matiereId(chapitre.getMatiere().getMatiereId())
                .build();
    }
}
