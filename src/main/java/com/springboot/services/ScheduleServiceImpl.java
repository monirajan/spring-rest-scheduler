package com.springboot.services;

import com.springboot.api.v1.mapper.ScheduleMapper;
import com.springboot.api.v1.model.ScheduleDTO;
import com.springboot.domain.Frequency;
import com.springboot.domain.Schedule;
import com.springboot.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDTO> listAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::scheduleToscheduleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> listScheduleByDate(String date) throws ParseException {

        Date dateToCompare = new SimpleDateFormat("dd MMM yyyy").parse(date);

        Predicate<Schedule> withinRange = schedule -> schedule.getStartDate().compareTo(dateToCompare) * dateToCompare.compareTo(schedule.getEndDate()) >= 0;
        Predicate<Schedule> isRepeat = schedule -> schedule.getRepeat() == true;
        Predicate<Schedule> isDaily = schedule -> schedule.getFrequency() == Frequency.Daily;
        Predicate<Schedule> isWeeklyDate = schedule -> schedule.getFrequency() == Frequency.Weekly &&
                (dateToCompare.getDate() - schedule.getStartDate().getDate())%7 == 0;
        Predicate<Schedule> isMonthlyDate = schedule -> schedule.getFrequency() == Frequency.Monthly &&
                (dateToCompare.getDate() == schedule.getStartDate().getDate());
        Predicate<Schedule> isWeekday = schedule -> schedule.getFrequency() == Frequency.Weekdays &&
                dateToCompare.getDay() != 0;
        Predicate<Schedule> validFreq = isDaily.or(isWeeklyDate).or(isMonthlyDate).or(isWeekday);

        Predicate<Schedule> repeat = withinRange.and(isRepeat).and(validFreq);
        Predicate<Schedule> nonRepeat = withinRange.and(isRepeat.negate());

        return scheduleRepository.findAll()
                .stream()
                .filter(repeat.or(nonRepeat))
                .map(scheduleMapper::scheduleToscheduleDTO)
                .collect(Collectors.toList());
    }
}
