package io.github.cursoms.mscreditassessor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAssessmentClient {
    private List<CreditCardApproved> creditCards;


}
