package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrainingMapper {

    private final UserRepository userRepository;

    public TrainingMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    public Training toEntity(TrainingDto dto) {
        Optional<User> user = userRepository.findById(dto.userId());
        if (user.isEmpty()) {
            throw new TrainingNotFoundException(dto.userId());
        }
        return new Training(
                user.get(),
                dto.startTime(),
                dto.endTime(),
                dto.activityType(),
                dto.distance(),
                dto.averageSpeed()
        );
    }
}
