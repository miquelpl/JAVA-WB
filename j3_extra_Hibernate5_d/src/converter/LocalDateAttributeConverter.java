package converter;


import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate locDate) {
		return (locDate == null ? null : Date.valueOf(locDate));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		return (sqlDate == null ? null : sqlDate.toLocalDate());
	}

	public static LocalDate convertDateToLocalDate(java.util.Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime()).toLocalDate();
		}
		return null;
	}

	// public static java.util.Date convertLocalDateToDate(LocalDate localDate) {
	// if (localDate != null) {
	// return java.sql.Date.valueOf(localDate);
	// }
	// return null;
	// }
}