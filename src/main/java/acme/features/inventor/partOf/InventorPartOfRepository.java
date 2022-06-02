package acme.features.inventor.partOf;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.artifact.Artifact;
import acme.artifact.ArtifactType;
import acme.artifact.PartOf;
import acme.artifact.ToolKit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPartOfRepository extends AbstractRepository {

	@Query("select t from ToolKit t where t.id = :id")
	ToolKit findOneToolKitById(int id);

	@Query("select t from ToolKit t")
	Collection<ToolKit> findManyToolKits();

	@Query("select p from PartOf p")
	Collection<PartOf> findManyPartOf();
	
	@Query("select p from PartOf p where p.id = :id")
	PartOf findOnePartOfById(int id);
	
	@Query("select p from PartOf p where p.toolkit.id = :masterId")
	Collection<PartOf> findManyPartOfByMasterId(int masterId);
	
	@Query("select p from PartOf p where p.toolkit.id = :masterId and p.artifact.type = :type")
	Collection<PartOf> findManyPartOfByMasterId(int masterId, ArtifactType type);
	
	@Query("select a from Artifact a left join PartOf po on po.artifact=a where (po.toolkit is null or po.toolkit<>:toolkit) and a.type=:type and a.inventor=:inventor")
	Collection<Artifact> findArtifactsByTypeToolKitInventor(ArtifactType type, ToolKit toolkit, Inventor inventor);

	@Query("select i from Inventor i where i.id=:id")
	Inventor findOneInventorByInventorId(int id);

}
