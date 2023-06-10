package Backend.Dto;

import Backend.entities.CycleScolaire;
import Backend.entities.Systeme;
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
public class CycleScolaireDto {
    private String cycleScolaireId;
    private String cycleScolaireName;
    private String sousSystemeEducatifId;
    @Builder.Default
    private List<NiveauDto> niveaux = new ArrayList<>();

    public static CycleScolaireDto map(CycleScolaire cycleScolaire) {
        return  CycleScolaireDto.builder()
                .cycleScolaireId(cycleScolaire.getCycleScolaireId())
                .cycleScolaireName(cycleScolaire.getCycleScolaireName())
                .sousSystemeEducatifId(cycleScolaire.getSousSystemeEducatif().getSystemeId())
                .build();
    }
}
