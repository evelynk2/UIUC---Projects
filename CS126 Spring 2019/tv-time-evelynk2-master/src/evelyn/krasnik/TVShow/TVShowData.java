package evelyn.krasnik.TVShow;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;


public class TVShowData {


    public static String getFileContentsAsString(String filename) {
        final Path path = FileSystems.getDefault().getPath("data", filename);

        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            System.out.println("Couldn't find file: " + filename);
            System.exit(-1);
            return null;
        }
    }


    // returns all of the episodes from a given season
    public static List<TVShow> parseBySeason(List<TVShow> episodes, int season) throws Exception {

        if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        List<TVShow> returnEpisodes = new LinkedList<>(episodes);
        for (TVShow ep : episodes) {
            if (ep.getSeason() != season) {
                returnEpisodes.remove(ep);
            }
        }

        return returnEpisodes;
    }


    // returns all of the episodes with an episode name contains a given String
    public static List<TVShow> parseByTitleKeyword(List<TVShow> episodes, String keyword) {
        if (keyword == null) {
            throw new NullPointerException("Keyword cannot be null.");
        } else if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        List<TVShow> returnEpisodes = new LinkedList<>(episodes);
        for (TVShow ep : episodes) {
            if (!ep.getEpisodeName().contains(keyword)) {
                returnEpisodes.remove(ep);
            }
        }
        return returnEpisodes;
    }


    // returns all of the episodes with an airdate in a given year
    public static List<TVShow> parseByAiryear(List<TVShow> episodes, String airyear) {
        if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }
        List<TVShow> returnEpisodes = new LinkedList<>(episodes);
        for (TVShow ep : episodes) {
            if (!ep.getAiryear().equals(airyear)) {
                returnEpisodes.remove(ep);
            }
        }
        return returnEpisodes;
    }

    // returns all of the episodes with the summary containing a given String
    public static List<TVShow> parseBySummaryKeyword(List<TVShow> episodes, String keyword) {
        if (keyword == null) {
            throw new NullPointerException("Keyword cannot be null.");
        } else if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        List<TVShow> returnEpisodes = new LinkedList<>(episodes);
        for (TVShow ep : episodes) {
            if (!ep.getSummary().contains(keyword)) {
                returnEpisodes.remove(ep);
            }
        }
        return returnEpisodes;
    }


    // returns the total number of episodes in a given collection of episodes
    public static int totalNumberOfEpisodes(List<TVShow> episodes) {
        if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        return episodes.size();
    }

    // returns the airdate of the most recent episode in a given collection of episodes
    public static String mostRecentAirdate(List<TVShow> episodes) {
        if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        TVShow mostRecent = episodes.get(0);
        for (TVShow ep : episodes) {
            if (mostRecent.getSeason() < ep.getSeason()) {
                mostRecent = ep;
            } else if (mostRecent.getSeason() == ep.getSeason()) {
                if (mostRecent.getEpisodeNumber() < ep.getEpisodeNumber()) {
                    mostRecent = ep;
                }
            }
        }
        return mostRecent.getAirdate();
    }

    // returns the average length of an episode in a given collection of episodes
    public static int averageLengthOfAnEpisode(List<TVShow> episodes) {
        if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        int averageLength = 0;
        for (TVShow ep : episodes) {
            averageLength += ep.getRuntime();
        }
        averageLength /= episodes.size();
        return averageLength;
    }


    // return the name of the oldest episode in a given collection of episodes
    public static String oldestEpisodeName(List<TVShow> episodes) {
        if (episodes == null) {
            throw new NullPointerException("List must contain episodes.");
        } else if (episodes.size() == 0) {
            throw new IllegalArgumentException("List must contain episodes.");
        }

        TVShow oldestEpisode = episodes.get(0);
        for (TVShow ep : episodes) {
            if (oldestEpisode.getSeason() > ep.getSeason()) {
                oldestEpisode = ep;
            } else if (oldestEpisode.getSeason() == ep.getSeason()) {
                if (oldestEpisode.getEpisodeNumber() > ep.getEpisodeNumber()) {
                    oldestEpisode = ep;
                }
            }
        }
        return oldestEpisode.getEpisodeName();
    }


}
