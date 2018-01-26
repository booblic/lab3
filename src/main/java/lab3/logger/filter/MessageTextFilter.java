package lab3.logger.filter;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "MessageTextFilter")
@XmlRootElement
public class MessageTextFilter extends Filter {

    private String keyWord;

    private boolean invert;

    public MessageTextFilter(String keyWord, boolean... invert) {
        this.keyWord = keyWord;
        if (invert.length != 0) {
            this.invert = invert[0];
        } else {
            this.invert = false;
        }
    }

    public MessageTextFilter() {}

    public String getKeyWord() {
        return keyWord;
    }

    @XmlElement(name = "KeyWord")
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public boolean getInvert() {
        return invert;
    }

    @XmlAttribute(name = "Invert")
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    @Override
    public boolean filter(Level level, Class clazz, String threadName, String message, Throwable... exeption) {

        if (message.indexOf(keyWord) == 0) {
            return false;
        }
        return true;
    }
}
