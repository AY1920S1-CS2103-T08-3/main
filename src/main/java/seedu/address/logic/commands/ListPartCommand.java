package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.competition.Competition;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Name;

/**
 * Lists all Participations for a specific competition.
 */
public class ListPartCommand extends Command {

    public static final String COMMAND_WORD = "listPart";

    public static final String MESSAGE_SUCCESS = "Listed all participants for ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " Competition Name";
    public static final String MESSAGE_COMPETITION_NOT_FOUND = "The competition with the given name does not exist : ";

    private final Name competitionName;

    /**
     * Creates a ListPartCommand which will list out all Participations
     * participating in the specified competition.
     * @param competitionName name of competition whose participants we want to list out
     */
    public ListPartCommand(Name competitionName) {
        requireNonNull(competitionName);
        this.competitionName = competitionName;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<Competition> competitionList = model.getFilteredCompetitionList();
        Competition competition = null;
        for (Competition c : competitionList) {
            if (c.getName().equals(competitionName)) {
                competition = c;
                break;
            }
        }

        if (competition == null) {
            return new CommandResult(MESSAGE_COMPETITION_NOT_FOUND + competitionName);
        }


        Competition finalCompetition = competition;
        Predicate<Participation> filterByCompetition = p -> p.getCompetition().isSameElement(finalCompetition);
        model.updateFilteredParticipationList(filterByCompetition);
        return new CommandResult(MESSAGE_SUCCESS + competition.toString());
    }
}
