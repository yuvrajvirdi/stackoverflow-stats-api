package stackoverflowstats.api.service;

import stackoverflowstats.api.model.Stats;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Service
public class ApiServiceImplentation implements ApiService{
    @Override
    public Stats getStats(String userId){
        return scrapeStats(userId);
    }

    private Stats scrapeStats(String userIdStr){
        try {
            WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);

            // user profile page
            String url = "https://stackoverflow.com/users/" + userIdStr;
            HtmlPage profilePage = client.getPage(url);

            // userId, username
            List<HtmlDivision> usernameList = profilePage.getByXPath("//div[@class='flex--item mb12 fs-headline2 lh-xs']");
            int userId = Integer.parseInt(userIdStr);
            String username = usernameList.get(0).asNormalizedText();

            // reputation, reached, answers, questions
            List<HtmlDivision> statsList = profilePage.getByXPath("//div[@class='fs-body3 fc-dark']");
            int reputation = toInt(statsList.get(0).asNormalizedText());
            int reached = toInt(statsList.get(1).asNormalizedText());
            int answers = toInt(statsList.get(2).asNormalizedText());
            int questions = toInt(statsList.get(3).asNormalizedText());

            List<HtmlDivision> percList = profilePage.getByXPath("//div[@class='s-anchors s-anchors__inherit js-rank-badge']");
            String percentile = percList.size() != 0 ? percList.get(0).asNormalizedText() : "average user";

            List<HtmlDivision> badgesList = profilePage.getByXPath("//div[@class='fs-title fw-bold fc-black-800']");
            int badges = badgesList.size() != 0 ? getNumBadges(badgesList) : 0;

            client.close();
            return new Stats("200", "retrieved", userId, username, reputation, reached, answers, questions, percentile, badges);
        } catch (IOException err) {
            return Stats.error("400", err.getMessage());
        }
    }

    private int getNumBadges(List<HtmlDivision> list){
        int badges = 0;
        for (int i = 0; i < list.size(); i++){
            badges += toInt(list.get(i).asNormalizedText());
        } 
        return badges;
    }

    private int toInt(String str){
        str = str.replaceAll(",", "");
        if (str.charAt(str.length()-1) == 'k'){
            String newStr = "";
            String dblStr = "";
            for (int i = 0; i < str.length()-1; i++){
                if (str.charAt(i) == '.'){
                    for (int j = 0; j < str.length()-1; j++){
                        dblStr += str.charAt(j);
                    }
                    return (int)(Double.parseDouble(dblStr)*1000.0);
                }
                newStr += str.charAt(i);
            }
            return Integer.parseInt(newStr)*1000;
        } else if (str.charAt(str.length()-1) == 'm'){
            String newStr = "";
            String dblStr = "";
            for (int i = 0; i < str.length()-1; i++){
                newStr += str.charAt(i);
                if (str.charAt(i) == '.'){
                    for (int j = 0; j < str.length()-1; j++){
                        dblStr += str.charAt(j);
                    }
                    return (int)(Double.parseDouble(dblStr)*1000000.0);
                }
            }
            return Integer.parseInt(newStr)*1000000;
        }
        return Integer.parseInt(str);
    }
}
