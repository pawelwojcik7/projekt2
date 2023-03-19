package is.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "laptops")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Laptops {

    @XmlAttribute(name = "moddate")
    private String modDate;

    @XmlElement(name = "laptop")
    private List<Laptop> laptops;

}
