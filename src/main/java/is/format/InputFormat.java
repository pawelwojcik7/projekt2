package is.format;

import is.model.ComputerInfo;

public abstract class InputFormat<Format> {
    public abstract ComputerInfo toComputerInfo();

    public abstract Format convert(ComputerInfo computerInfo);

}
