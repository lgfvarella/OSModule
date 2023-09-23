package com.lgfvarella.os.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIO(1, "MÉDIA"), ALTA(2, "ALTA");

	private Integer cod;
	private String description;

	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Prioridade x : Prioridade.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Prioridade Inválida!" + cod);

	}

}
