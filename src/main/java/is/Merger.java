package is;

import is.model.ComputerInfo;
import is.model.MergeStatistic;
import is.model.Pair;
import is.validator.models.RecordType;

import java.util.List;

public class Merger {


    public Merger() {
    }

    public MergeStatistic compareAndMerge(List<ComputerInfo> list, List<Pair<ComputerInfo, RecordType>> mainList) {
        if (mainList.isEmpty()) {
            mainList.addAll(list.stream().map(e -> new Pair<ComputerInfo, RecordType>(e, RecordType.NEW)).toList());
            return new MergeStatistic(mainList.size(), 0);
        } else {
            List<ComputerInfo> list1 = mainList.stream().map(Pair::getLeft).toList();
            List<Pair<ComputerInfo, RecordType>> duplicates = list.stream().filter(e -> !list1.contains(e)).map(e -> new Pair<ComputerInfo, RecordType>(e, RecordType.DUPLICATED)).toList(); //TODO : poprawic zeby nie analizowało id
            List<Pair<ComputerInfo, RecordType>> newElements = list.stream().filter(list1::contains).map(e -> new Pair<ComputerInfo, RecordType>(e, RecordType.NEW)).toList();
            mainList.addAll(duplicates);
            mainList.addAll(newElements);
            return new MergeStatistic(newElements.size(), duplicates.size());
        }
    }


}
