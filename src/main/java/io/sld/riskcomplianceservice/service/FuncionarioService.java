package io.sld.riskcomplianceservice.service;

import io.sld.riskcomplianceservice.domain.Funcionario;
import io.sld.riskcomplianceservice.repository.FuncionarioRepository;
import io.sld.riskcomplianceservice.service.dto.FuncionarioDTO;
import io.sld.riskcomplianceservice.service.mapper.FuncionarioMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Funcionario}.
 */
@Service
@Transactional
public class FuncionarioService {

    private final Logger log = LoggerFactory.getLogger(FuncionarioService.class);

    private final FuncionarioRepository funcionarioRepository;

    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    /**
     * Save a funcionario.
     *
     * @param funcionarioDTO the entity to save.
     * @return the persisted entity.
     */
    public FuncionarioDTO save(FuncionarioDTO funcionarioDTO) {
        log.debug("Request to save Funcionario : {}", funcionarioDTO);
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);
        return funcionarioMapper.toDto(funcionario);
    }

    /**
     * Update a funcionario.
     *
     * @param funcionarioDTO the entity to save.
     * @return the persisted entity.
     */
    public FuncionarioDTO update(FuncionarioDTO funcionarioDTO) {
        log.debug("Request to save Funcionario : {}", funcionarioDTO);
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario = funcionarioRepository.save(funcionario);
        return funcionarioMapper.toDto(funcionario);
    }

    /**
     * Partially update a funcionario.
     *
     * @param funcionarioDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FuncionarioDTO> partialUpdate(FuncionarioDTO funcionarioDTO) {
        log.debug("Request to partially update Funcionario : {}", funcionarioDTO);

        return funcionarioRepository
            .findById(funcionarioDTO.getId())
            .map(existingFuncionario -> {
                funcionarioMapper.partialUpdate(existingFuncionario, funcionarioDTO);

                return existingFuncionario;
            })
            .map(funcionarioRepository::save)
            .map(funcionarioMapper::toDto);
    }

    /**
     * Get all the funcionarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Funcionarios");
        return funcionarioRepository.findAll(pageable).map(funcionarioMapper::toDto);
    }

    /**
     * Get one funcionario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FuncionarioDTO> findOne(Long id) {
        log.debug("Request to get Funcionario : {}", id);
        return funcionarioRepository.findById(id).map(funcionarioMapper::toDto);
    }

    /**
     * Delete the funcionario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Funcionario : {}", id);
        funcionarioRepository.deleteById(id);
    }
}
