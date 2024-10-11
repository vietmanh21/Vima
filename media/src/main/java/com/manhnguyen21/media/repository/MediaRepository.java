package com.manhnguyen21.media.repository;

import com.manhnguyen21.media.dto.response.NoFileMediaVm;
import com.manhnguyen21.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query(value = "select new com.manhnguyen21.media.dto.response.NoFileMediaVm(m.id, m.caption, m.fileName, m.mediaType)\n" +
            "        from Media m where m.id = :id")
    NoFileMediaVm findByIdWithoutFileInReturn(Long id);
}
