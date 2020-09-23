package com.ayshiktest.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AyshikModel {

	@Getter @Setter
	 private String name;
	@Getter @Setter
	private String roll;
	@Getter @Setter
	private String address;

	public AyshikModel(String name, String roll, String address) {
		super();
		this.name = name;
		this.roll = roll;
		this.address = address;
	}
}
