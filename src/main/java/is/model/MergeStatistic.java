package is.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MergeStatistic {
    private final Integer newRecords;
    private final Integer duplicates;
}
