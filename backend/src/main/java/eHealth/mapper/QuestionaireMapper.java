package eHealth.mapper;

import eHealth.dto.QuestionnaireDto;
import eHealth.entity.Questionnaire;
import org.springframework.stereotype.Component;

@Component
public class QuestionaireMapper {

    public Questionnaire dtoToEntity(QuestionnaireDto questionnaireDto) {
        return new Questionnaire(questionnaireDto.getGender(), questionnaireDto.getAge(), questionnaireDto.getLocation(), questionnaireDto.getPain(),questionnaireDto.getPain_date(), questionnaireDto.getPain_amount(), questionnaireDto.getNotizen(), questionnaireDto.getAddtext(), questionnaireDto.getBodypart(), questionnaireDto.getSymptom());
    }
}
