package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.List;

public interface TrainingService {
    List<TrainingDto> findAllTrainings();
    List<TrainingDto> findTrainingsForUser(Long userId);
    TrainingDto createTraining(TrainingDto trainingDto);
    List<TrainingDto> findCompletedTrainings(String date);
    List<TrainingDto> findTrainingsByActivity(ActivityType activityType);
    TrainingDto updateTraining(Long trainingId, TrainingDto trainingDto);
}
