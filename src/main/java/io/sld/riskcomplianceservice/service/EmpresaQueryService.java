package io.sld.riskcomplianceservice.service;

import io.sld.riskcomplianceservice.domain.*; // for static metamodels
import io.sld.riskcomplianceservice.domain.Empresa;
import io.sld.riskcomplianceservice.repository.EmpresaRepository;
import io.sld.riskcomplianceservice.service.criteria.EmpresaCriteria;
import io.sld.riskcomplianceservice.service.dto.EmpresaDTO;
import io.sld.riskcomplianceservice.service.mapper.EmpresaMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Empresa} entities in the database.
 * The main input is a {@link EmpresaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmpresaDTO} or a {@link Page} of {@link EmpresaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmpresaQueryService extends QueryService<Empresa> {

    private final Logger log = LoggerFactory.getLogger(EmpresaQueryService.class);

    private final EmpresaRepository empresaRepository;

    private final EmpresaMapper empresaMapper;

    public EmpresaQueryService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    /**
     * Return a {@link List} of {@link EmpresaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmpresaDTO> findByCriteria(EmpresaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Empresa> specification = createSpecification(criteria);
        return empresaMapper.toDto(empresaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmpresaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmpresaDTO> findByCriteria(EmpresaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Empresa> specification = createSpecification(criteria);
        return empresaRepository.findAll(specification, page).map(empresaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmpresaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Empresa> specification = createSpecification(criteria);
        return empresaRepository.count(specification);
    }

    /**
     * Function to convert {@link EmpresaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Empresa> createSpecification(EmpresaCriteria criteria) {
        Specification<Empresa> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Empresa_.id));
            }
            if (criteria.getIdnVarEmpresa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdnVarEmpresa(), Empresa_.idnVarEmpresa));
            }
            if (criteria.getnVarNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getnVarNome(), Empresa_.nVarNome));
            }
            if (criteria.getnVarDescricao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getnVarDescricao(), Empresa_.nVarDescricao));
            }
            if (criteria.getClienteExternoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getClienteExternoId(),
                            root -> root.join(Empresa_.clienteExternos, JoinType.LEFT).get(ClienteExterno_.id)
                        )
                    );
            }
            if (criteria.getFornecedorExternoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getFornecedorExternoId(),
                            root -> root.join(Empresa_.fornecedorExternos, JoinType.LEFT).get(FornecedorExterno_.id)
                        )
                    );
            }
            if (criteria.getComplianceExternoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getComplianceExternoId(),
                            root -> root.join(Empresa_.complianceExternos, JoinType.LEFT).get(ComplianceExterno_.id)
                        )
                    );
            }
            if (criteria.getComplianceInternoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getComplianceInternoId(),
                            root -> root.join(Empresa_.complianceInternos, JoinType.LEFT).get(ComplianceInterno_.id)
                        )
                    );
            }
            if (criteria.getFuncionarioId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getFuncionarioId(),
                            root -> root.join(Empresa_.funcionarios, JoinType.LEFT).get(Funcionario_.id)
                        )
                    );
            }
            if (criteria.getMacroProcessoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getMacroProcessoId(),
                            root -> root.join(Empresa_.macroProcessos, JoinType.LEFT).get(MacroProcesso_.id)
                        )
                    );
            }
            if (criteria.getMacroProcessoOrganogramaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getMacroProcessoOrganogramaId(),
                            root -> root.join(Empresa_.macroProcessoOrganogramas, JoinType.LEFT).get(MacroProcessoOrganograma_.id)
                        )
                    );
            }
            if (criteria.getOrganogramaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getOrganogramaId(),
                            root -> root.join(Empresa_.organogramas, JoinType.LEFT).get(Organograma_.id)
                        )
                    );
            }
            if (criteria.getProcessoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getProcessoId(), root -> root.join(Empresa_.processos, JoinType.LEFT).get(Processo_.id))
                    );
            }
            if (criteria.getUsuarioId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUsuarioId(), root -> root.join(Empresa_.usuarios, JoinType.LEFT).get(Usuario_.id))
                    );
            }
        }
        return specification;
    }
}
