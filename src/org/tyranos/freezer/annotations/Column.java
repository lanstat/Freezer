package org.tyranos.freezer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author lanstat
 *
 */
@Target(value=ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Column {
	public DataType Type() default DataType.Int32;
	public int Accuracy() default 0;
	public boolean PrimaryKey() default false;
	public boolean Nullable() default true;
	public String DefaultValue() default "NULL";
}
