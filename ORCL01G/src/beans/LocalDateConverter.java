package beans;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.primefaces.convert.DateTimeConverter;

@FacesConverter(value = LocalDateConverter.ID)
public class LocalDateConverter extends DateTimeConverter {
	public static final String ID = "LocalDateConverter";

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		LocalDate ldate = null;
		Date date = null;
		Object o = super.getAsObject(facesContext, uiComponent, value);
		if (o == null) {
			return null;
		}
		if (o instanceof Date) {
			date = (Date) o;
			Instant instant = Instant.ofEpochMilli(date.getTime());
			ldate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
			return ldate;
		} else {
			throw new IllegalArgumentException(String
					.format("value=%s could not be converted to a LocalDate, result super.getAsObject=%s", value, o));
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value == null) {
			return super.getAsString(facesContext, uiComponent, value);
		}
		if (value instanceof LocalDate) {
			LocalDate lDate = (LocalDate) value;
			Instant instant = lDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			return super.getAsString(facesContext, uiComponent, date);
		} else if(value instanceof Date) {
			return super.getAsString(facesContext, uiComponent, value);
		} else {
			throw new IllegalArgumentException(String.format("value=%s is not a instanceof LocalDate", value));
		}
	}

}
