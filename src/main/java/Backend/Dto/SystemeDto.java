package Backend.Dto;

import Backend.entities.Systeme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SystemeDto {
    private String systemeId;
    private String systemeName;
    private String systemeEducatifParentId;
    @Builder.Default
    private List<SystemeDto> sousSystemesEducatifs = new ArrayList<>();
    @Builder.Default
    private List<CycleScolaireDto> cycles= new ArrayList<>();

    public static SystemeDto map(Systeme systeme) {
        return  SystemeDto.builder()
                .systemeId(systeme.getSystemeId())
                .systemeName(systeme.getSystemeName())
                .systemeEducatifParentId(systeme.getSystemeEducatifParent().getSystemeId())
                .build();
    }
}
