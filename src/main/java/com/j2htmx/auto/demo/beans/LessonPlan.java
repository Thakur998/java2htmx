package com.j2htmx.auto.demo.beans;

import java.time.Instant;
import java.time.LocalDate;


public record LessonPlan(
        String id,

        String title,
        String description,

        String subject,
        String grade,

        Integer weekNumber,

        LessonStatus status,

        String teacherName,

        LocalDate plannedDate,

        Integer estimatedMinutes,

        String learningObjective,

        String teachingMethod,

        String resources,

        String assessmentMethod,

        ApprovalStatus approvalStatus,

        String coordinatorComments,

        String coordinatorName,

        Instant createdAt,

        Instant updatedAt
) {
}



