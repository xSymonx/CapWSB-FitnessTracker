package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        return ResponseEntity.ok(trainingService.findAllTrainings());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(trainingService.findTrainingsForUser(userId));
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto trainingDto) {
        return ResponseEntity.ok(trainingService.createTraining(trainingDto));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TrainingDto>> getCompletedTrainings(@RequestParam String date) {
        return ResponseEntity.ok(trainingService.findCompletedTrainings(date));
    }

    @GetMapping("/activity")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        return ResponseEntity.ok(trainingService.findTrainingsByActivity(activityType));
    }

    @PutMapping("/{trainingId}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto trainingDto) {
        return ResponseEntity.ok(trainingService.updateTraining(trainingId, trainingDto));
    }
}
