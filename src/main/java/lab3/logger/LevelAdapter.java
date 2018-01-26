package lab3.logger;

import lab3.logger.level.Level;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author VYZH
 * @since 22.01.2018
 */
public class LevelAdapter extends XmlAdapter<String, Level> {
    @Override
    public Level unmarshal(String v) throws Exception {
        return Level.valueOf(v);
    }

    @Override
    public String marshal(Level v) throws Exception {
        return v.levelStr;
    }
}
