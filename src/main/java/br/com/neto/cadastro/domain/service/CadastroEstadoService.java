package br.com.neto.cadastro.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.neto.cadastro.domain.model.Estado;
import br.com.neto.cadastro.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois está em uso";

	@Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado buscar(Long estadoId) {
        return this.estadoRepository.findById(estadoId).orElseThrow(() -> new RuntimeException(String.format("Não existe um cadastro de estado com código %d", estadoId)));
    }

    @Transactional
    public void excluir(Long estadoId) {
        try {
            this.estadoRepository.deleteById(estadoId);
            this.estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }
}
