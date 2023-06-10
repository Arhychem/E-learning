package Backend.Dto;

import Backend.entities.Matiere;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MatiereDto {
    private String matiereId;
    private String matiereName;
    private String niveauId;
    @Builder.Default
    private List<ChapitreDto> chapitreDtoList = new ArrayList<>();

    public static MatiereDto map(Matiere matiere) {
        return  MatiereDto.builder()
                .matiereId(matiere.getMatiereId())
                .matiereName(matiere.getMatiereName())
                .niveauId(matiere.getNiveau().getNiveauId())
                .build();
    }
}
