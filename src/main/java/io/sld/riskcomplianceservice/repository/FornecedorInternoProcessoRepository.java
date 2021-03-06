package io.sld.riskcomplianceservice.repository;

import io.sld.riskcomplianceservice.domain.FornecedorInternoProcesso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FornecedorInternoProcesso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FornecedorInternoProcessoRepository
    extends JpaRepository<FornecedorInternoProcesso, Long>, JpaSpecificationExecutor<FornecedorInternoProcesso> {}
