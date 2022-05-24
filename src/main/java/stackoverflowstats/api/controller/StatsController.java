package stackoverflowstats.api.controller;

import stackoverflowstats.api.model.Stats;
import stackoverflowstats.api.service.ApiService;
import stackoverflowstats.api.service.ApiServiceImplentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class StatsController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/stats")
    public Stats getStats(@RequestParam(value = "userId", defaultValue = "error") String userId){
        if (!userId.equals("error")){
            ApiServiceImplentation service = new ApiServiceImplentation();
            return apiService.getStats(userId);
        } else {
            return Stats.error("400", "Something went wrong");
        }
    }
}
