package model.dto;

import java.sql.Date;

public record StaffResponseDto(
        int id,
        String staffName,
        Date staffDOB,
        String staffGender,
        String staffPosition,
        Boolean status
) {
}
