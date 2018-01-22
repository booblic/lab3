package lab3.logger.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "configurations")
public class Conf {
    @XmlElement(name = "configuration")
    public List<Configuration> configurations = new ArrayList<>();

    public Conf() {}
}
