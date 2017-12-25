package lab3.logger.layout;

import java.util.ArrayList;
import java.util.List;

public class Layout {
    public List<String> layots = new ArrayList<>();

    public Layout(String format) {
        layots = splitter(format);
    }

    private List<String> splitter(String format) {
        List<String> list = new ArrayList<>();
        for (String str: format.split(" ")) {
            list.add(str);
        }
        return list;
    }
}
