package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService, TrainingProvider {
    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public List<TrainingDto> findAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> findTrainingsForUser(Long userId) {
        return trainingRepository.findAll()
                .stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDto createTraining(TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingRepository.save(training);
        return trainingMapper.toDto(savedTraining);
    }

    @Override
    public List<TrainingDto> findCompletedTrainings(String date) {
        // Implement logic to find completed trainings by date
        // Placeholder
        return List.of();
    }

    @Override
    public List<TrainingDto> findTrainingsByActivity(ActivityType activityType) {
        return trainingRepository.findAll()
                .stream()
                .filter(training -> training.getActivityType() == activityType)
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDto updateTraining(Long trainingId, TrainingDto trainingDto) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));
        training.setStartTime(trainingDto.startTime());
        training.setEndTime(trainingDto.endTime());
        training.setActivityType(trainingDto.activityType());
        training.setDistance(trainingDto.distance());
        training.setAverageSpeed(trainingDto.averageSpeed());
        Training updatedTraining = trainingRepository.save(training);
        return trainingMapper.toDto(updatedTraining);
    }
}
