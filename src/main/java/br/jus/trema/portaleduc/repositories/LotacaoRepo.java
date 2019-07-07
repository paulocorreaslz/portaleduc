package br.jus.trema.portaleduc.repositories;

import java.util.List;

import br.jus.trema.portaleduc.models.Lotacao;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.vraptorbridge.repositories.AbstractRepo;

public interface LotacaoRepo extends AbstractRepo<Lotacao> {
	
	Lotacao getLotacaoAnterior(String matricula, Turma turma);
	
}
