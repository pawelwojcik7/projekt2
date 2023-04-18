package is.format.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@XmlRootElement(name = "laptops")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaptopsXML{

    @XmlAttribute(name = "moddate")
    private String modDate;

    @XmlElement(name = "laptop")
    private List<XMLInputFormat> XMLInputFormats;

    public LaptopsXML(List<XMLInputFormat> XMLInputFormats) {
        this.modDate = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.XMLInputFormats = XMLInputFormats;
    }
}
