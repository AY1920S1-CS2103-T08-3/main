package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.session.SessionInformation;

/**
 * An UI component which displays information of a {@code ParticipationAttempt} for the {@code Session}.
 */
public class SessionCard extends UiPart<Region> {
    public static final Logger logger = LogsCenter.getLogger(SessionCard.class);
    public static final String FXML = "SessionCard.fxml";

    private final SessionInformation sessionInformation;

    @FXML
    private Label compName;

    @FXML
    private Label name;

    @FXML
    private Label weight;

    @FXML
    private Label attempt;

    public SessionCard(ObservableValue<SessionInformation> sessionInfo) {
        super(FXML);
        this.sessionInformation = sessionInfo.getValue();

        if (!sessionInformation.getHasOngoingCompetition()) {
            compName.setText(null);
        } else {
            compName.setText(sessionInformation.getCompetitionName());
        }

        if (!sessionInformation.getHasNextAttempt()) {
            name.setText(null);
            weight.setText(null);
            attempt.setText(null);
        } else {
            name.setText(sessionInformation.getParticipationName());
            weight.setText(sessionInformation.getAttemptWeight());
            attempt.setText(sessionInformation.getLiftAndAttemptNumber());
        }
    }
}
