package evelyn.krasnik.TVShow;

import java.util.List;

/*
 * created by evelynk2
 */

public class TVShow {

    private String name;
    private String summary;
    private String premiered;
    private String airdate;
    private int season;
    private int number;
    private int runtime;
    private int id;
    private List<String> genres;


    public void setEpisodeName(String episodeName) {
        name = episodeName;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPremiereDate(String premiereDate) {
        premiered = premiereDate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }


    public void setSeason(int season) {
        this.season = season;
    }

    public void setEpisodeNumber(int episodeNumber) {
        number = episodeNumber;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setEpisodeID(int id) {
        this.id = id;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getEpisodeName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisodeNumber() {
        return number;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getPremiereDate() {
        return premiered;
    }

    public String getAirdate() {
        return airdate;
    }

    public String getAiryear() {
        return airdate.substring(0,4);
    }

    public List<String> getGenres() {
        return genres;
    }


    // returns all of the episodes in a given season
    public List<TVShow> getAllEpisodesInASeason(List<TVShow> episodes, int season) {
        try {
            return TVShowData.parseBySeason(episodes, season);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // returns all of the episodes with an episode name contains a given String
    public List<TVShow> getAllEpisodesContainingKeywordInTitle(List<TVShow> episodes, String keyword) {
        try {
            return TVShowData.parseByTitleKeyword(episodes, keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // returns all of the episodes with an airdate in a given year
    public List<TVShow> getAllEpisodesInAYear(List<TVShow> episodes, String airyear) {
        return TVShowData.parseByAiryear(episodes, airyear);
    }

    // returns all of the episodes with the summary containing a given String
    public List<TVShow> getAllEpisodesContainingKeywordInSummary(List<TVShow> episodes, String keyword) {
        try {
            return TVShowData.parseBySummaryKeyword(episodes, keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // returns the total number of episodes in a given collection of episodes
    public int getTotalNumberOfEpisodes(List<TVShow> episodes) {
        try {
            return TVShowData.totalNumberOfEpisodes(episodes);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // returns the airdate of the most recent episode in a given collection of episodes
    public String getMostRecentEpisodeAirdate(List<TVShow> episodes) {
        try {
            return TVShowData.mostRecentAirdate(episodes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // returns the average length of an episode in a given collection of episodes
    public int getAverageLengthofEpisode(List<TVShow> episodes) {
        try {
            return TVShowData.averageLengthOfAnEpisode(episodes);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // return the name of the oldest episode in a given collection of episodes
    public String getOldestEpisodeTitle(List<TVShow> episodes) {
        try {
            return TVShowData.oldestEpisodeName(episodes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
