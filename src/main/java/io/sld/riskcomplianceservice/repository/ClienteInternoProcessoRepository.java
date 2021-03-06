package io.sld.riskcomplianceservice.repository;

import io.sld.riskcomplianceservice.domain.ClienteInternoProcesso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ClienteInternoProcesso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClienteInternoProcessoRepository
    extends JpaRepository<ClienteInternoProcesso, Long>, JpaSpecificationExecutor<ClienteInternoProcesso> {}
