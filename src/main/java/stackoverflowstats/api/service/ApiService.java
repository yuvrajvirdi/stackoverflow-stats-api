package stackoverflowstats.api.service;

import stackoverflowstats.api.model.Stats;

public interface ApiService {
    Stats getStats(String userId);
}
