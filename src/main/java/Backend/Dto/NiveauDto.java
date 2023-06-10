package Backend.Dto;

import Backend.entities.Niveau;
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
public class NiveauDto {
    private String niveauId;
    private String niveauName;
    private String cycleScolaireId;
    @Builder.Default
    private List<MatiereDto> matiereDtoList = new ArrayList<>();

    public static NiveauDto map(Niveau niveau) {
        return  NiveauDto.builder()
                .niveauId(niveau.getNiveauId())
                .niveauName(niveau.getNiveauName())
                .cycleScolaireId(niveau.getCycleScolaire().getCycleScolaireId())
                .build();
    }
}
