package data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EulerFunction {
    @Getter
    private final Function constantFunction;
    @Getter
    private final Function analyticalAnswer;
    @Getter
    private final Function function;
}
