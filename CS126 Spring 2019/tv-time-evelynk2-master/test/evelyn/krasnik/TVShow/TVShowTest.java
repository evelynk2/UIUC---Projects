package evelyn.krasnik.TVShow;

import com.google.gson.Gson;
import evelyn.krasnik.TVShow.TVShow;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TVShowTest {

    // first episode of Narcos
    private static final String NARCOS_EPISODE_JSON = "{\n" +
            "            \"id\":203469,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/203469/narcos-1x01-descenso\",\n" +
            "            \"name\":\"Descenso\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":1,\n" +
            "            \"airdate\":\"2015-08-28\",\n" +
            "            \"airtime\":\"\",\n" +
            "            \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41472.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41472.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>A Chilean drugsproducer, the chemist Cockroach, brings his product to the Colombian smuggler Pablo Escobar. DEA-Agent Steve Murphy is fighting drugs in Bogota.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/203469\"\n" +
            "               }\n" +
            "            }\n" +
            "         }";

    // all episodes of Narcos
    private static final String NARCOS_EPISODE_ARRAY_JSON = "[{\n" +
            "              \"id\":203469,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/203469/narcos-1x01-descenso\",\n" +
            "              \"name\":\"Descenso\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":1,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41472.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41472.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>A Chilean drugsproducer, the chemist Cockroach, brings his product to the Colombian smuggler Pablo Escobar. DEA-Agent Steve Murphy is fighting drugs in Bogota.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/203469\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208978,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208978/narcos-1x02-the-sword-of-simon-bolivar\",\n" +
            "              \"name\":\"The Sword of Simón Bolívar\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":2,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41473.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41473.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The communistic radical group M-19 are counteracting the drug lords and Murphy's new partner Peña teaches him the Colombian way of law enforcement.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208978\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208979,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208979/narcos-1x03-the-men-of-always\",\n" +
            "              \"name\":\"The Men of Always\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":3,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41474.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41474.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Murphy is confronted with corruption in the Colombian government once he tries to discolate Escobar's his political ambition together with his partner Peña.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208979\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208980,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208980/narcos-1x04-the-palace-in-flames\",\n" +
            "              \"name\":\"The Palace in Flames\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":4,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41475.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41475.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Despite a new extradition treaty, the USA is increasing their budget to fight communism. It will create new challenges for Murphy and Peña to hunt for Pablo Escobar.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208980\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208981,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208981/narcos-1x05-there-will-be-a-future\",\n" +
            "              \"name\":\"There Will Be a Future\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":5,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41476.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41476.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo's extreme methods is almost leading to a war between drug lords, Carillo and the government. Peña tries to protect his witness Elisa.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208981\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208982,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208982/narcos-1x06-explosivos\",\n" +
            "              \"name\":\"Explosivos\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":6,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41477.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41477.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Gacha was almost caught by Peña and Carillo. Murphy tries to protect candidate Gaviria against assassins with ties to Pablo as she is in favour for extradition.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208982\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208983,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208983/narcos-1x07-you-will-cry-tears-of-blood\",\n" +
            "              \"name\":\"You Will Cry Tears of Blood\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":7,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41478.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41478.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo went to hiding as political tides are turning against him, but he finds a way to beat back. The CIA is finally offering their help to Peña and Murphy.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208983\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208984,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208984/narcos-1x08-la-gran-mentira\",\n" +
            "              \"name\":\"La Gran Mentira\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":8,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41479.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41479.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>A tragic mistake forces the government to change strategies to fight Pablo, but Pablo has bigger issues as there is danger from within his empire.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208984\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208985,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208985/narcos-1x09-la-catedral\",\n" +
            "              \"name\":\"La Catedral\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":9,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41480.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41480.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The hunt for Pablo seems to be over once he signs a deal with the government, but Murphy, Peña and the Calicartel have different thoughts about that.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208985\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208986,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208986/narcos-1x10-despegue\",\n" +
            "              \"name\":\"Despegue\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":10,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41481.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41481.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo's activities in prison forces the government to use extreme actions. Murphy and Peña have to deal with a problematic situation.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208986\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832098,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832098/narcos-2x01-free-at-last\",\n" +
            "              \"name\":\"Free at Last\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":1,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182764.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182764.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>In the aftermath of a massive military effort to take Pablo into custody, the family reunites while enemies worry. Steve and Connie fight about safety.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832098\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832099,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832099/narcos-2x02-cambalache\",\n" +
            "              \"name\":\"Cambalache\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":2,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182765.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182765.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Tata gets impatient with life on the run. Pablo responds to President Gaviria's reward offer. Steve and Javier meet their new boss.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832099\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832100,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832100/narcos-2x03-our-man-in-madrid\",\n" +
            "              \"name\":\"Our Man in Madrid\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":3,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182776.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182776.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>President Gaviria has a new job for an old colleague. The Search Bloc's new tactics shake up Pablo, but also unsettle Steve and Javier.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832100\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832101,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832101/narcos-2x04-the-good-the-bad-and-the-dead\",\n" +
            "              \"name\":\"The Good, the Bad, and the Dead\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":4,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182778.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182778.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Cali cartel discusses moving in on Pablo's territory. Limon proposes a plan to Maritza. Tata gets a gun for protection.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832101\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832102,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832102/narcos-2x05-the-enemies-of-my-enemy\",\n" +
            "              \"name\":\"The Enemies of My Enemy\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":5,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182782.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182782.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Search Bloc gets a new leader. Javier loses faith in the system. Pablo brings Tata brother Carlos down from Miami to cheer her up.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832102\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832103,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832103/narcos-2x06-los-pepes\",\n" +
            "              \"name\":\"Los Pepes\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":6,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182783.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182783.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The newly-formed Los Pepes want to destroy Pablo and his empire. Tata's brother urges her to leave and seek safety with her children.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832103\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832256,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832256/narcos-2x07-deutschland-93\",\n" +
            "              \"name\":\"Deutschland 93\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":7,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182784.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182784.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>As the danger intensifies for the Escobars, Pablo sends his family to another country. Gaviria weighs the opportunity to use them as leverage.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832256\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832257,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832257/narcos-2x08-exit-el-patron\",\n" +
            "              \"name\":\"Exit El Patrón\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":8,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182786.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182786.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Colombia begins to turn on Escobar after his latest terrorist attack. Tata receives help from an unlikely ally. Quica gets increasingly anxious.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832257\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832258,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832258/narcos-2x09-nuestra-finca\",\n" +
            "              \"name\":\"Nuestra Finca\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":9,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182788.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182788.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo is reunited with an estranged family member. Judy Moncada's life is put in danger. The DEA and CIA clash over how to handle Los Pepes.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832258\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832259,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832259/narcos-2x10-al-fin-cayo\",\n" +
            "              \"name\":\"Al Fin Cayó!\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":10,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182789.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182789.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Javier deals with the repercussions from Judy's interview. Tata tries to convince Pablo to surrender for the sake of his children.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832259\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1249969,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1249969/narcos-3x01-the-kingpin-strategy\",\n" +
            "              \"name\":\"The Kingpin Strategy\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":1,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317059.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317059.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Gentlemen of Cali gather their associates together for a big surprise announcement about the future of their business.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1249969\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285381,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285381/narcos-3x02-the-cali-kgb\",\n" +
            "              \"name\":\"The Cali KGB\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":2,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317072.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317072.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>A gas incident threatens to disrupt the Cali-government deal and Jorge is asked to help out. Peña grapples with his former Los Pepes connection.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285381\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285382,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285382/narcos-3x03-follow-the-money\",\n" +
            "              \"name\":\"Follow the Money\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":3,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317399.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317399.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Rodriguez brothers go into hiding during negotiations. Pacho meets with the Lord of the Skies in Mexico. Peña's new DEA team visits Cali.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285382\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285383,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285383/narcos-3x04-checkmate\",\n" +
            "              \"name\":\"Checkmate\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":4,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/315938.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/315938.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Peña hatches a plan to try to capture Cali leader Gilberto Rodriguez. Amado proposes a business idea to Pacho.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285383\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285384,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285384/narcos-3x05-mro\",\n" +
            "              \"name\":\"MRO\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":5,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330716.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330716.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Paranoid about leaks, Miguel cracks down on his security. Pacho makes a decision about his new offer. Peña works on cultivating a witness.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285384\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285385,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285385/narcos-3x06-best-laid-plans\",\n" +
            "              \"name\":\"Best Laid Plans\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":6,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330745.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330745.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Jorge takes a dangerous risk. An accident in New York threatens to expose Chepe. Peña travels to Curaçao to arrest a potential witness.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285385\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285391,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285391/narcos-3x07-sin-salida\",\n" +
            "              \"name\":\"Sin Salida\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":7,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330755.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330755.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Peña plans another covert operation to take down a key Cali cartel member, but he risks running out of time during his search.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285391\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285392,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285392/narcos-3x08-convivir\",\n" +
            "              \"name\":\"Convivir\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":8,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330778.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330778.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>David seeks revenge on behalf of his father, putting Enrique in danger. Peña asks Don Berna for help on a rescue mission.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285392\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285393,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285393/narcos-3x09-todos-los-hombres-del-presidente\",\n" +
            "              \"name\":\"Todos Los Hombres del Presidente\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":9,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317021.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317021.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>David follows his suspicions. Peña is shocked to discover the depth of corruption in the Colombian government. Miguel is sought after once again.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285393\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285394,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285394/narcos-3x10-going-back-to-cali\",\n" +
            "              \"name\":\"Going Back to Cali\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":10,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317018.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317018.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>David and Peña are in a race against each other to find Pallomari. Peña makes a serious decision about the future of his career.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285394\"\n" +
            "                 }\n" +
            "              }\n" +
            "           }]";

    // whole Narcos show object
    private static final String NARCOS_JSON = "{\n" +
            "     \"id\":2705,\n" +
            "     \"url\":\"http://www.tvmaze.com/shows/2705/narcos\",\n" +
            "     \"name\":\"evelyn.krasnik.TVShow.TVShow\",\n" +
            "     \"type\":\"Scripted\",\n" +
            "     \"language\":\"English\",\n" +
            "     \"genres\":[\n" +
            "        \"Drama\",\n" +
            "        \"Crime\"\n" +
            "     ],\n" +
            "     \"status\":\"Ended\",\n" +
            "     \"runtime\":60,\n" +
            "     \"premiered\":\"2015-08-28\",\n" +
            "     \"officialSite\":\"https://www.netflix.com/nl/title/80025172\",\n" +
            "     \"schedule\":{\n" +
            "        \"time\":\"\",\n" +
            "        \"days\":[\n" +
            "           \"Friday\"\n" +
            "        ]\n" +
            "     },\n" +
            "     \"rating\":{\n" +
            "        \"average\":8.4\n" +
            "     },\n" +
            "     \"weight\":95,\n" +
            "     \"network\":null,\n" +
            "     \"webChannel\":{\n" +
            "        \"id\":1,\n" +
            "        \"name\":\"Netflix\",\n" +
            "        \"country\":null\n" +
            "     },\n" +
            "     \"externals\":{\n" +
            "        \"tvrage\":37241,\n" +
            "        \"thetvdb\":282670,\n" +
            "        \"imdb\":\"tt2707408\"\n" +
            "     },\n" +
            "     \"image\":{\n" +
            "        \"medium\":\"http://static.tvmaze.com/uploads/images/medium_portrait/177/444565.jpg\",\n" +
            "        \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/177/444565.jpg\"\n" +
            "     },\n" +
            "     \"summary\":\"<p><b>evelyn.krasnik.TVShow.TVShow</b> chronicle the life and death of drug lord Pablo Escobar the ruthless boss of the Medellin Cartel and a known terrorist who was also a congressman, a family man and revered by the poor as a new Robin Hood.</p>\",\n" +
            "     \"updated\":1547142293,\n" +
            "     \"_links\":{\n" +
            "        \"self\":{\n" +
            "           \"href\":\"http://api.tvmaze.com/shows/2705\"\n" +
            "        },\n" +
            "        \"previousepisode\":{\n" +
            "           \"href\":\"http://api.tvmaze.com/episodes/1285394\"\n" +
            "        }\n" +
            "     },\n" +
            "     \"_embedded\":{\n" +
            "        \"episodes\":[\n" +
            "           {\n" +
            "              \"id\":203469,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/203469/narcos-1x01-descenso\",\n" +
            "              \"name\":\"Descenso\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":1,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41472.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41472.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>A Chilean drugsproducer, the chemist Cockroach, brings his product to the Colombian smuggler Pablo Escobar. DEA-Agent Steve Murphy is fighting drugs in Bogota.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/203469\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208978,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208978/narcos-1x02-the-sword-of-simon-bolivar\",\n" +
            "              \"name\":\"The Sword of Simón Bolívar\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":2,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41473.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41473.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The communistic radical group M-19 are counteracting the drug lords and Murphy's new partner Peña teaches him the Colombian way of law enforcement.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208978\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208979,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208979/narcos-1x03-the-men-of-always\",\n" +
            "              \"name\":\"The Men of Always\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":3,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41474.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41474.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Murphy is confronted with corruption in the Colombian government once he tries to discolate Escobar's his political ambition together with his partner Peña.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208979\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208980,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208980/narcos-1x04-the-palace-in-flames\",\n" +
            "              \"name\":\"The Palace in Flames\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":4,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41475.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41475.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Despite a new extradition treaty, the USA is increasing their budget to fight communism. It will create new challenges for Murphy and Peña to hunt for Pablo Escobar.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208980\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208981,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208981/narcos-1x05-there-will-be-a-future\",\n" +
            "              \"name\":\"There Will Be a Future\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":5,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41476.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41476.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo's extreme methods is almost leading to a war between drug lords, Carillo and the government. Peña tries to protect his witness Elisa.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208981\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208982,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208982/narcos-1x06-explosivos\",\n" +
            "              \"name\":\"Explosivos\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":6,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41477.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41477.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Gacha was almost caught by Peña and Carillo. Murphy tries to protect candidate Gaviria against assassins with ties to Pablo as she is in favour for extradition.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208982\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208983,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208983/narcos-1x07-you-will-cry-tears-of-blood\",\n" +
            "              \"name\":\"You Will Cry Tears of Blood\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":7,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41478.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41478.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo went to hiding as political tides are turning against him, but he finds a way to beat back. The CIA is finally offering their help to Peña and Murphy.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208983\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208984,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208984/narcos-1x08-la-gran-mentira\",\n" +
            "              \"name\":\"La Gran Mentira\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":8,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41479.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41479.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>A tragic mistake forces the government to change strategies to fight Pablo, but Pablo has bigger issues as there is danger from within his empire.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208984\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208985,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208985/narcos-1x09-la-catedral\",\n" +
            "              \"name\":\"La Catedral\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":9,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41480.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41480.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The hunt for Pablo seems to be over once he signs a deal with the government, but Murphy, Peña and the Calicartel have different thoughts about that.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208985\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":208986,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/208986/narcos-1x10-despegue\",\n" +
            "              \"name\":\"Despegue\",\n" +
            "              \"season\":1,\n" +
            "              \"number\":10,\n" +
            "              \"airdate\":\"2015-08-28\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2015-08-28T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/16/41481.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/16/41481.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo's activities in prison forces the government to use extreme actions. Murphy and Peña have to deal with a problematic situation.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/208986\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832098,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832098/narcos-2x01-free-at-last\",\n" +
            "              \"name\":\"Free at Last\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":1,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182764.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182764.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>In the aftermath of a massive military effort to take Pablo into custody, the family reunites while enemies worry. Steve and Connie fight about safety.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832098\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832099,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832099/narcos-2x02-cambalache\",\n" +
            "              \"name\":\"Cambalache\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":2,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182765.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182765.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Tata gets impatient with life on the run. Pablo responds to President Gaviria's reward offer. Steve and Javier meet their new boss.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832099\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832100,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832100/narcos-2x03-our-man-in-madrid\",\n" +
            "              \"name\":\"Our Man in Madrid\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":3,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182776.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182776.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>President Gaviria has a new job for an old colleague. The Search Bloc's new tactics shake up Pablo, but also unsettle Steve and Javier.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832100\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832101,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832101/narcos-2x04-the-good-the-bad-and-the-dead\",\n" +
            "              \"name\":\"The Good, the Bad, and the Dead\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":4,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182778.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182778.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Cali cartel discusses moving in on Pablo's territory. Limon proposes a plan to Maritza. Tata gets a gun for protection.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832101\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832102,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832102/narcos-2x05-the-enemies-of-my-enemy\",\n" +
            "              \"name\":\"The Enemies of My Enemy\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":5,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182782.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182782.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Search Bloc gets a new leader. Javier loses faith in the system. Pablo brings Tata brother Carlos down from Miami to cheer her up.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832102\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832103,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832103/narcos-2x06-los-pepes\",\n" +
            "              \"name\":\"Los Pepes\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":6,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182783.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182783.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The newly-formed Los Pepes want to destroy Pablo and his empire. Tata's brother urges her to leave and seek safety with her children.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832103\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832256,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832256/narcos-2x07-deutschland-93\",\n" +
            "              \"name\":\"Deutschland 93\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":7,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182784.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182784.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>As the danger intensifies for the Escobars, Pablo sends his family to another country. Gaviria weighs the opportunity to use them as leverage.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832256\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832257,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832257/narcos-2x08-exit-el-patron\",\n" +
            "              \"name\":\"Exit El Patrón\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":8,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182786.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182786.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Colombia begins to turn on Escobar after his latest terrorist attack. Tata receives help from an unlikely ally. Quica gets increasingly anxious.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832257\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832258,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832258/narcos-2x09-nuestra-finca\",\n" +
            "              \"name\":\"Nuestra Finca\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":9,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182788.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182788.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Pablo is reunited with an estranged family member. Judy Moncada's life is put in danger. The DEA and CIA clash over how to handle Los Pepes.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832258\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":832259,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/832259/narcos-2x10-al-fin-cayo\",\n" +
            "              \"name\":\"Al Fin Cayó!\",\n" +
            "              \"season\":2,\n" +
            "              \"number\":10,\n" +
            "              \"airdate\":\"2016-09-02\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2016-09-02T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/73/182789.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/73/182789.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Javier deals with the repercussions from Judy's interview. Tata tries to convince Pablo to surrender for the sake of his children.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/832259\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1249969,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1249969/narcos-3x01-the-kingpin-strategy\",\n" +
            "              \"name\":\"The Kingpin Strategy\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":1,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317059.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317059.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Gentlemen of Cali gather their associates together for a big surprise announcement about the future of their business.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1249969\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285381,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285381/narcos-3x02-the-cali-kgb\",\n" +
            "              \"name\":\"The Cali KGB\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":2,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317072.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317072.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>A gas incident threatens to disrupt the Cali-government deal and Jorge is asked to help out. Peña grapples with his former Los Pepes connection.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285381\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285382,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285382/narcos-3x03-follow-the-money\",\n" +
            "              \"name\":\"Follow the Money\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":3,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317399.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317399.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>The Rodriguez brothers go into hiding during negotiations. Pacho meets with the Lord of the Skies in Mexico. Peña's new DEA team visits Cali.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285382\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285383,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285383/narcos-3x04-checkmate\",\n" +
            "              \"name\":\"Checkmate\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":4,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/315938.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/315938.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Peña hatches a plan to try to capture Cali leader Gilberto Rodriguez. Amado proposes a business idea to Pacho.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285383\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285384,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285384/narcos-3x05-mro\",\n" +
            "              \"name\":\"MRO\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":5,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330716.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330716.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Paranoid about leaks, Miguel cracks down on his security. Pacho makes a decision about his new offer. Peña works on cultivating a witness.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285384\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285385,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285385/narcos-3x06-best-laid-plans\",\n" +
            "              \"name\":\"Best Laid Plans\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":6,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330745.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330745.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Jorge takes a dangerous risk. An accident in New York threatens to expose Chepe. Peña travels to Curaçao to arrest a potential witness.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285385\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285391,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285391/narcos-3x07-sin-salida\",\n" +
            "              \"name\":\"Sin Salida\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":7,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330755.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330755.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>Peña plans another covert operation to take down a key Cali cartel member, but he risks running out of time during his search.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285391\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285392,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285392/narcos-3x08-convivir\",\n" +
            "              \"name\":\"Convivir\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":8,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/132/330778.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/132/330778.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>David seeks revenge on behalf of his father, putting Enrique in danger. Peña asks Don Berna for help on a rescue mission.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285392\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285393,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285393/narcos-3x09-todos-los-hombres-del-presidente\",\n" +
            "              \"name\":\"Todos Los Hombres del Presidente\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":9,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317021.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317021.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>David follows his suspicions. Peña is shocked to discover the depth of corruption in the Colombian government. Miguel is sought after once again.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285393\"\n" +
            "                 }\n" +
            "              }\n" +
            "           },\n" +
            "           {\n" +
            "              \"id\":1285394,\n" +
            "              \"url\":\"http://www.tvmaze.com/episodes/1285394/narcos-3x10-going-back-to-cali\",\n" +
            "              \"name\":\"Going Back to Cali\",\n" +
            "              \"season\":3,\n" +
            "              \"number\":10,\n" +
            "              \"airdate\":\"2017-09-01\",\n" +
            "              \"airtime\":\"\",\n" +
            "              \"airstamp\":\"2017-09-01T12:00:00+00:00\",\n" +
            "              \"runtime\":60,\n" +
            "              \"image\":{\n" +
            "                 \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/126/317018.jpg\",\n" +
            "                 \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/126/317018.jpg\"\n" +
            "              },\n" +
            "              \"summary\":\"<p>David and Peña are in a race against each other to find Pallomari. Peña makes a serious decision about the future of his career.</p>\",\n" +
            "              \"_links\":{\n" +
            "                 \"self\":{\n" +
            "                    \"href\":\"http://api.tvmaze.com/episodes/1285394\"\n" +
            "                 }\n" +
            "              }\n" +
            "           }\n" +
            "        ]\n" +
            "     }\n" +
            "}\n" +
            "\n" +
            "\n";

    // all episodes of Westworld
    private static final String WESTWORLD_EPISODE_ARRAY_JSON = "[{\n" +
            "            \"id\":869671,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/869671/westworld-1x01-the-original\",\n" +
            "            \"name\":\"The Original\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":1,\n" +
            "            \"airdate\":\"2016-10-02\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-10-03T01:00:00+00:00\",\n" +
            "            \"runtime\":68,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/78/195475.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/78/195475.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>A woman named Dolores is a free spirit in the Old West... and unaware that she's actually an android, programmed to entertain rich guests seeking to act out their fantasies in an idealized vision of the 1880s. However, the people in charge soon realize that their androids are acting in ways that they didn't anticipate.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/869671\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":911201,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/911201/westworld-1x02-chestnut\",\n" +
            "            \"name\":\"Chestnut\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":2,\n" +
            "            \"airdate\":\"2016-10-09\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-10-10T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/78/195436.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/78/195436.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Bernard suspects that someone is sabotaging the hosts. Meanwhile, the Man continues his search for the deeper game, and Maeve finds herself in a world of hurt... literally.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/911201\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":911204,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/911204/westworld-1x03-the-stray\",\n" +
            "            \"name\":\"The Stray\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":3,\n" +
            "            \"airdate\":\"2016-10-16\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-10-17T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/79/198183.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/79/198183.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Bernard continues to investigate Dolores' supposed malfunction, and realizes that he has something unexpected on his hands. Meanwhile, Robert talks about the early days of the park, and Teddy receives a new role and narrative.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/911204\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":911205,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/911205/westworld-1x04-dissonance-theory\",\n" +
            "            \"name\":\"Dissonance Theory\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":4,\n" +
            "            \"airdate\":\"2016-10-23\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-10-24T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/80/201367.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/80/201367.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>While Dolores joins William and Logan on their adventure, the Man in Black continues his search for the entrance to the Maze and meets Armistice. Meanwhile, Robert reveals some of his true power to Theresa and advises her to stay out of his way.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/911205\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":927174,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/927174/westworld-1x05-contrapasso\",\n" +
            "            \"name\":\"Contrapasso\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":5,\n" +
            "            \"airdate\":\"2016-10-30\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-10-31T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/81/204265.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/81/204265.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Dolores takes the first step on her path of discovery by deciding to write a new story for herself... where she isn't the damsel. Meanwhile, Elise discovers the Woodcutter's secret, and Robert pays the Man a visit.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/927174\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":932057,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/932057/westworld-1x06-the-adversary\",\n" +
            "            \"name\":\"The Adversary\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":6,\n" +
            "            \"airdate\":\"2016-11-06\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-11-07T02:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/82/207324.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/82/207324.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>While Maeve convinces Felix to show her \\\"upstairs,\\\" Teddy and the Man close in on the maze. Meanwhile, Bernard and Elsie close in on the identity of the person who is stealing secrets from the park, while Lee meets with a Delos executive director.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/932057\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":938404,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/938404/westworld-1x07-trompe-loeil\",\n" +
            "            \"name\":\"Trompe L'Oeil\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":7,\n" +
            "            \"airdate\":\"2016-11-13\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-11-14T02:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/83/209400.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/83/209400.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>While William and Dolores travel to the unclaimed territories, Charlotte and Theresa make plans to force Robert out of the park using Clementine. Meanwhile, Maeve continues her plans to escape Westworld.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/938404\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":943065,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/943065/westworld-1x08-trace-decay\",\n" +
            "            \"name\":\"Trace Decay\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":8,\n" +
            "            \"airdate\":\"2016-11-20\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-11-21T02:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/84/211554.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/84/211554.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Bernard tries to cope with what he did under Robert's orders. Meanwhile, William and Dolores find a town, and Maeve remembers more of her past. Meanwhile, the Man reveals his past to Teddy.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/943065\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":943066,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/943066/westworld-1x09-the-well-tempered-clavier\",\n" +
            "            \"name\":\"The Well-Tempered Clavier\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":9,\n" +
            "            \"airdate\":\"2016-11-27\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-11-28T02:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/85/213932.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/85/213932.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Bernard forces Robert to take him back into his own memories, while the Man meets with Charlotte. Meanwhile, Dolores escapes the Confederado camp and William gives Logan new orders.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/943066\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":943067,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/943067/westworld-1x10-the-bicameral-mind\",\n" +
            "            \"name\":\"The Bicameral Mind\",\n" +
            "            \"season\":1,\n" +
            "            \"number\":10,\n" +
            "            \"airdate\":\"2016-12-04\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2016-12-05T02:00:00+00:00\",\n" +
            "            \"runtime\":90,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/86/216453.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/86/216453.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Delores finds out the truth about William's fate. Meanwhile, Maeve organizes an escape plan, only to discover that someone else is pulling the strings. And Robert plays the final piece in his grand narrative.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/943067\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1214222,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1214222/westworld-2x01-journey-into-night\",\n" +
            "            \"name\":\"Journey Into Night\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":1,\n" +
            "            \"airdate\":\"2018-04-22\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-04-23T01:00:00+00:00\",\n" +
            "            \"runtime\":74,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/152/381118.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/152/381118.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>In the aftermath of the host rebellion, the rescue team finds Bernard and the new Head of Operations asks for his help. Meanwhile, Delores promises to show Teddy the truth, and Maeve convinces Lee and Hector to help her find her daughter.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1214222\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1440915,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1440915/westworld-2x02-reunion\",\n" +
            "            \"name\":\"Reunion\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":2,\n" +
            "            \"airdate\":\"2018-04-29\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-04-30T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/153/382957.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/153/382957.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Dolores looks for allies sympathetic to her cause and shows Teddy the truth of his existence. In the past, Dolores gets her first taste of the outside world and William shows her a weapon of mass destruction.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1440915\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1440916,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1440916/westworld-2x03-virtu-e-fortuna\",\n" +
            "            \"name\":\"Virtù e Fortuna\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":3,\n" +
            "            \"airdate\":\"2018-05-06\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-05-07T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/154/386069.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/154/386069.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>While Delores makes a deal with the Confederados, Maeve and her group find Felix and Sylvester. Meanwhile, a new Guest comes to Westworld, and Bernard has a meeting of minds with Peter.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1440916\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1440917,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1440917/westworld-2x04-the-riddle-of-the-sphinx\",\n" +
            "            \"name\":\"The Riddle of the Sphinx\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":4,\n" +
            "            \"airdate\":\"2018-05-13\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-05-14T01:00:00+00:00\",\n" +
            "            \"runtime\":71,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/155/387630.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/155/387630.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>William pays his recuperating father-in-law several visits. Meanwhile, the Man continues to play Robert's game, and Bernard remembers some of his missing time.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1440917\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1446362,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1446362/westworld-2x05-akane-no-mai\",\n" +
            "            \"name\":\"Akane No Mai\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":5,\n" +
            "            \"airdate\":\"2018-05-20\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-05-21T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/155/389365.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/155/389365.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>While Maeve and her people explore Shogun World, Delores realizes that the Teddy she knows can't be a part of the new world coming.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1446362\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1453278,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1453278/westworld-2x06-phase-space\",\n" +
            "            \"name\":\"Phase Space\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":6,\n" +
            "            \"airdate\":\"2018-05-27\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-05-28T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/156/390990.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/156/390990.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Maeve finds her daughter, Delores finds the \\\"real\\\" Teddy, Grace finds her father, and Charlotte and Stubbs find Peter.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1453278\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1459961,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1459961/westworld-2x07-les-ecorches\",\n" +
            "            \"name\":\"Les Écorchés\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":7,\n" +
            "            \"airdate\":\"2018-06-03\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-06-04T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/156/392143.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/156/392143.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Charlotte and Bernard review Bernard's memories to discover what happened to the missing control unit. Meanwhile, Robert has a chat with Bernard, and Maeve confronts her former tormentor.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1459961\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1459788,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1459788/westworld-2x08-kiksuya\",\n" +
            "            \"name\":\"Kiksuya\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":8,\n" +
            "            \"airdate\":\"2018-06-10\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-06-11T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/157/393811.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/157/393811.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>Another of Westworld's Host revolutionaries is revealed. Meanwhile, Emily finds the Man and convinces the Ghost Nation to hand him over to her to ensure his suffering.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1459788\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1459789,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1459789/westworld-2x09-vanishing-point\",\n" +
            "            \"name\":\"Vanishing Point\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":9,\n" +
            "            \"airdate\":\"2018-06-17\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-06-18T01:00:00+00:00\",\n" +
            "            \"runtime\":60,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/158/395754.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/158/395754.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>The Man learns that his grip on reality isn't what he thought, as he considers how his wife died. Meanwhile, Dolores continues her trek across the park to the Valley Beyond, and Bernard receives another visit from Robert.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1459789\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         {\n" +
            "            \"id\":1459790,\n" +
            "            \"url\":\"http://www.tvmaze.com/episodes/1459790/westworld-2x10-the-passenger\",\n" +
            "            \"name\":\"The Passenger\",\n" +
            "            \"season\":2,\n" +
            "            \"number\":10,\n" +
            "            \"airdate\":\"2018-06-24\",\n" +
            "            \"airtime\":\"21:00\",\n" +
            "            \"airstamp\":\"2018-06-25T01:00:00+00:00\",\n" +
            "            \"runtime\":90,\n" +
            "            \"image\":{\n" +
            "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/158/397018.jpg\",\n" +
            "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/158/397018.jpg\"\n" +
            "            },\n" +
            "            \"summary\":\"<p>The Man faces a personal reckoning, while Akecheta and the others find the door. Meanwhile, Bernard meets with Robert one last time after choosing how to deal with Dolores.</p>\",\n" +
            "            \"_links\":{\n" +
            "               \"self\":{\n" +
            "                  \"href\":\"http://api.tvmaze.com/episodes/1459790\"\n" +
            "               }\n" +
            "            }\n" +
            "         }]";

    // whole Westworld show object
    private static final String WESTWORLD_JSON = "{\n" +
        "   \"id\":1371,\n" +
                "   \"url\":\"http://www.tvmaze.com/shows/1371/westworld\",\n" +
                "   \"name\":\"Westworld\",\n" +
                "   \"type\":\"Scripted\",\n" +
                "   \"language\":\"English\",\n" +
                "   \"genres\":[\n" +
                "      \"Drama\",\n" +
                "      \"Science-Fiction\",\n" +
                "      \"Western\"\n" +
                "   ],\n" +
                "   \"status\":\"Running\",\n" +
                "   \"runtime\":60,\n" +
                "   \"premiered\":\"2016-10-02\",\n" +
                "   \"officialSite\":\"http://www.hbo.com/westworld\",\n" +
                "   \"schedule\":{\n" +
                "      \"time\":\"21:00\",\n" +
                "      \"days\":[\n" +
                "         \"Sunday\"\n" +
                "      ]\n" +
                "   },\n" +
                "   \"rating\":{\n" +
                "      \"average\":8.5\n" +
                "   },\n" +
                "   \"weight\":98,\n" +
                "   \"network\":{\n" +
                "      \"id\":8,\n" +
                "      \"name\":\"HBO\",\n" +
                "      \"country\":{\n" +
                "         \"name\":\"United States\",\n" +
                "         \"code\":\"US\",\n" +
                "         \"timezone\":\"America/New_York\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"webChannel\":null,\n" +
                "   \"externals\":{\n" +
                "      \"tvrage\":37537,\n" +
                "      \"thetvdb\":296762,\n" +
                "      \"imdb\":\"tt0475784\"\n" +
                "   },\n" +
                "   \"image\":{\n" +
                "      \"medium\":\"http://static.tvmaze.com/uploads/images/medium_portrait/152/381152.jpg\",\n" +
                "      \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/152/381152.jpg\"\n" +
                "   },\n" +
                "   \"summary\":\"<p><b>Westworld</b> is a dark odyssey about the dawn of artificial consciousness and the evolution of sin. Set at the intersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged.</p>\",\n" +
                "   \"updated\":1545485044,\n" +
                "   \"_links\":{\n" +
                "      \"self\":{\n" +
                "         \"href\":\"http://api.tvmaze.com/shows/1371\"\n" +
                "      },\n" +
                "      \"previousepisode\":{\n" +
                "         \"href\":\"http://api.tvmaze.com/episodes/1459790\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"_embedded\":{\n" +
                "      \"episodes\":[\n" +
                "         {\n" +
                "            \"id\":869671,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/869671/westworld-1x01-the-original\",\n" +
                "            \"name\":\"The Original\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":1,\n" +
                "            \"airdate\":\"2016-10-02\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-10-03T01:00:00+00:00\",\n" +
                "            \"runtime\":68,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/78/195475.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/78/195475.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>A woman named Dolores is a free spirit in the Old West... and unaware that she's actually an android, programmed to entertain rich guests seeking to act out their fantasies in an idealized vision of the 1880s. However, the people in charge soon realize that their androids are acting in ways that they didn't anticipate.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/869671\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":911201,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/911201/westworld-1x02-chestnut\",\n" +
                "            \"name\":\"Chestnut\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":2,\n" +
                "            \"airdate\":\"2016-10-09\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-10-10T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/78/195436.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/78/195436.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Bernard suspects that someone is sabotaging the hosts. Meanwhile, the Man continues his search for the deeper game, and Maeve finds herself in a world of hurt... literally.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/911201\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":911204,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/911204/westworld-1x03-the-stray\",\n" +
                "            \"name\":\"The Stray\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":3,\n" +
                "            \"airdate\":\"2016-10-16\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-10-17T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/79/198183.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/79/198183.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Bernard continues to investigate Dolores' supposed malfunction, and realizes that he has something unexpected on his hands. Meanwhile, Robert talks about the early days of the park, and Teddy receives a new role and narrative.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/911204\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":911205,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/911205/westworld-1x04-dissonance-theory\",\n" +
                "            \"name\":\"Dissonance Theory\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":4,\n" +
                "            \"airdate\":\"2016-10-23\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-10-24T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/80/201367.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/80/201367.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>While Dolores joins William and Logan on their adventure, the Man in Black continues his search for the entrance to the Maze and meets Armistice. Meanwhile, Robert reveals some of his true power to Theresa and advises her to stay out of his way.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/911205\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":927174,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/927174/westworld-1x05-contrapasso\",\n" +
                "            \"name\":\"Contrapasso\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":5,\n" +
                "            \"airdate\":\"2016-10-30\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-10-31T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/81/204265.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/81/204265.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Dolores takes the first step on her path of discovery by deciding to write a new story for herself... where she isn't the damsel. Meanwhile, Elise discovers the Woodcutter's secret, and Robert pays the Man a visit.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/927174\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":932057,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/932057/westworld-1x06-the-adversary\",\n" +
                "            \"name\":\"The Adversary\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":6,\n" +
                "            \"airdate\":\"2016-11-06\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-11-07T02:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/82/207324.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/82/207324.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>While Maeve convinces Felix to show her \\\"upstairs,\\\" Teddy and the Man close in on the maze. Meanwhile, Bernard and Elsie close in on the identity of the person who is stealing secrets from the park, while Lee meets with a Delos executive director.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/932057\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":938404,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/938404/westworld-1x07-trompe-loeil\",\n" +
                "            \"name\":\"Trompe L'Oeil\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":7,\n" +
                "            \"airdate\":\"2016-11-13\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-11-14T02:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/83/209400.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/83/209400.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>While William and Dolores travel to the unclaimed territories, Charlotte and Theresa make plans to force Robert out of the park using Clementine. Meanwhile, Maeve continues her plans to escape Westworld.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/938404\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":943065,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/943065/westworld-1x08-trace-decay\",\n" +
                "            \"name\":\"Trace Decay\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":8,\n" +
                "            \"airdate\":\"2016-11-20\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-11-21T02:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/84/211554.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/84/211554.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Bernard tries to cope with what he did under Robert's orders. Meanwhile, William and Dolores find a town, and Maeve remembers more of her past. Meanwhile, the Man reveals his past to Teddy.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/943065\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":943066,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/943066/westworld-1x09-the-well-tempered-clavier\",\n" +
                "            \"name\":\"The Well-Tempered Clavier\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":9,\n" +
                "            \"airdate\":\"2016-11-27\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-11-28T02:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/85/213932.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/85/213932.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Bernard forces Robert to take him back into his own memories, while the Man meets with Charlotte. Meanwhile, Dolores escapes the Confederado camp and William gives Logan new orders.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/943066\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":943067,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/943067/westworld-1x10-the-bicameral-mind\",\n" +
                "            \"name\":\"The Bicameral Mind\",\n" +
                "            \"season\":1,\n" +
                "            \"number\":10,\n" +
                "            \"airdate\":\"2016-12-04\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2016-12-05T02:00:00+00:00\",\n" +
                "            \"runtime\":90,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/86/216453.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/86/216453.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Delores finds out the truth about William's fate. Meanwhile, Maeve organizes an escape plan, only to discover that someone else is pulling the strings. And Robert plays the final piece in his grand narrative.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/943067\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1214222,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1214222/westworld-2x01-journey-into-night\",\n" +
                "            \"name\":\"Journey Into Night\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":1,\n" +
                "            \"airdate\":\"2018-04-22\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-04-23T01:00:00+00:00\",\n" +
                "            \"runtime\":74,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/152/381118.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/152/381118.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>In the aftermath of the host rebellion, the rescue team finds Bernard and the new Head of Operations asks for his help. Meanwhile, Delores promises to show Teddy the truth, and Maeve convinces Lee and Hector to help her find her daughter.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1214222\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1440915,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1440915/westworld-2x02-reunion\",\n" +
                "            \"name\":\"Reunion\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":2,\n" +
                "            \"airdate\":\"2018-04-29\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-04-30T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/153/382957.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/153/382957.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Dolores looks for allies sympathetic to her cause and shows Teddy the truth of his existence. In the past, Dolores gets her first taste of the outside world and William shows her a weapon of mass destruction.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1440915\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1440916,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1440916/westworld-2x03-virtu-e-fortuna\",\n" +
                "            \"name\":\"Virtù e Fortuna\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":3,\n" +
                "            \"airdate\":\"2018-05-06\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-05-07T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/154/386069.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/154/386069.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>While Delores makes a deal with the Confederados, Maeve and her group find Felix and Sylvester. Meanwhile, a new Guest comes to Westworld, and Bernard has a meeting of minds with Peter.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1440916\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1440917,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1440917/westworld-2x04-the-riddle-of-the-sphinx\",\n" +
                "            \"name\":\"The Riddle of the Sphinx\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":4,\n" +
                "            \"airdate\":\"2018-05-13\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-05-14T01:00:00+00:00\",\n" +
                "            \"runtime\":71,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/155/387630.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/155/387630.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>William pays his recuperating father-in-law several visits. Meanwhile, the Man continues to play Robert's game, and Bernard remembers some of his missing time.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1440917\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1446362,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1446362/westworld-2x05-akane-no-mai\",\n" +
                "            \"name\":\"Akane No Mai\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":5,\n" +
                "            \"airdate\":\"2018-05-20\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-05-21T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/155/389365.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/155/389365.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>While Maeve and her people explore Shogun World, Delores realizes that the Teddy she knows can't be a part of the new world coming.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1446362\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1453278,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1453278/westworld-2x06-phase-space\",\n" +
                "            \"name\":\"Phase Space\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":6,\n" +
                "            \"airdate\":\"2018-05-27\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-05-28T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/156/390990.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/156/390990.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Maeve finds her daughter, Delores finds the \\\"real\\\" Teddy, Grace finds her father, and Charlotte and Stubbs find Peter.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1453278\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1459961,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1459961/westworld-2x07-les-ecorches\",\n" +
                "            \"name\":\"Les Écorchés\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":7,\n" +
                "            \"airdate\":\"2018-06-03\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-06-04T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/156/392143.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/156/392143.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Charlotte and Bernard review Bernard's memories to discover what happened to the missing control unit. Meanwhile, Robert has a chat with Bernard, and Maeve confronts her former tormentor.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1459961\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1459788,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1459788/westworld-2x08-kiksuya\",\n" +
                "            \"name\":\"Kiksuya\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":8,\n" +
                "            \"airdate\":\"2018-06-10\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-06-11T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/157/393811.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/157/393811.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>Another of Westworld's Host revolutionaries is revealed. Meanwhile, Emily finds the Man and convinces the Ghost Nation to hand him over to her to ensure his suffering.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1459788\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1459789,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1459789/westworld-2x09-vanishing-point\",\n" +
                "            \"name\":\"Vanishing Point\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":9,\n" +
                "            \"airdate\":\"2018-06-17\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-06-18T01:00:00+00:00\",\n" +
                "            \"runtime\":60,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/158/395754.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/158/395754.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>The Man learns that his grip on reality isn't what he thought, as he considers how his wife died. Meanwhile, Dolores continues her trek across the park to the Valley Beyond, and Bernard receives another visit from Robert.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1459789\"\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":1459790,\n" +
                "            \"url\":\"http://www.tvmaze.com/episodes/1459790/westworld-2x10-the-passenger\",\n" +
                "            \"name\":\"The Passenger\",\n" +
                "            \"season\":2,\n" +
                "            \"number\":10,\n" +
                "            \"airdate\":\"2018-06-24\",\n" +
                "            \"airtime\":\"21:00\",\n" +
                "            \"airstamp\":\"2018-06-25T01:00:00+00:00\",\n" +
                "            \"runtime\":90,\n" +
                "            \"image\":{\n" +
                "               \"medium\":\"http://static.tvmaze.com/uploads/images/medium_landscape/158/397018.jpg\",\n" +
                "               \"original\":\"http://static.tvmaze.com/uploads/images/original_untouched/158/397018.jpg\"\n" +
                "            },\n" +
                "            \"summary\":\"<p>The Man faces a personal reckoning, while Akecheta and the others find the door. Meanwhile, Bernard meets with Robert one last time after choosing how to deal with Dolores.</p>\",\n" +
                "            \"_links\":{\n" +
                "               \"self\":{\n" +
                "                  \"href\":\"http://api.tvmaze.com/episodes/1459790\"\n" +
                "               }\n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}";

    private static TVShow narcosEpisode;
    private static TVShow narcos;
    private static TVShow westworld;
    private static TVShow[] narcosEpisodeArray;
    private static TVShow[] westworldEpisodeArray;
    private static List<TVShow> narcosEpisodeList;
    private static List<TVShow> westworldEpisodeList;


    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        narcosEpisode = gson.fromJson(NARCOS_EPISODE_JSON, TVShow.class);
        narcosEpisodeArray = gson.fromJson(NARCOS_EPISODE_ARRAY_JSON, TVShow[].class);
        narcos = gson.fromJson(NARCOS_JSON, TVShow.class);
        narcosEpisodeList = Arrays.asList(narcosEpisodeArray);
        westworld = gson.fromJson(WESTWORLD_JSON, TVShow.class);
        westworldEpisodeArray = gson.fromJson(WESTWORLD_EPISODE_ARRAY_JSON, TVShow[].class);
        westworldEpisodeList = Arrays.asList(westworldEpisodeArray);
    }

    @Test
    public void fileAsStringCorrect() throws Exception {
        assertEquals(NARCOS_EPISODE_JSON, TVShowData.getFileContentsAsString("narcos_ep1_json.txt"));
        assertEquals(NARCOS_EPISODE_ARRAY_JSON, TVShowData.getFileContentsAsString("narcos_episodes_json.txt"));
        assertEquals(NARCOS_JSON, TVShowData.getFileContentsAsString("narcos_json.txt"));
        assertEquals(WESTWORLD_JSON, TVShowData.getFileContentsAsString("westworld_json.txt"));
    }

    @Test
    public void setNarcosEpisodeName() throws Exception {
        narcos.setEpisodeName("Descenso");
        assertEquals("Descenso", narcos.getEpisodeName());
    }

    @Test
    public void getNarcosEpisodeName() throws Exception {
        assertEquals("Descenso", narcosEpisode.getEpisodeName());
        assertEquals("The Sword of Simón Bolívar", narcosEpisodeArray[1].getEpisodeName());
        assertEquals("Los Pepes", narcosEpisodeArray[15].getEpisodeName());
    }


    @Test
    public void setNarcosSeason() throws Exception {
        narcos.setSeason(2);
        assertEquals(2, narcos.getSeason());
    }

    @Test
    public void getNarcosSeason() throws Exception {
        assertEquals(1, narcosEpisode.getSeason());
        assertEquals(2, narcosEpisodeArray[11].getSeason());
        assertEquals(3, narcosEpisodeArray[20].getSeason());
    }


    @Test
    public void setNarcosEpisodeNumber() throws Exception {
        narcos.setEpisodeNumber(5);
        assertEquals(5, narcos.getEpisodeNumber());
    }


    @Test
    public void getNarcosEpisodeNumber() throws Exception {
        assertEquals(1, narcosEpisode.getEpisodeNumber());
        assertEquals(5, narcosEpisodeArray[4].getEpisodeNumber());
        assertEquals(3, narcosEpisodeArray[12].getEpisodeNumber());
    }

    @Test
    public void setNarcosEpisodeRuntime() throws Exception {
        narcos.setRuntime(60);
        assertEquals(60, narcos.getRuntime());
    }

    @Test
    public void getNarcosEpisodeRuntime() throws Exception {
        assertEquals(60, narcosEpisode.getRuntime());
        assertEquals(60, narcosEpisodeArray[14].getRuntime());
    }

    @Test
    public void setNarcosEpisodeSummary() throws Exception {
        narcos.setSummary("Pablo Escobar");
        assertEquals("Pablo Escobar", narcos.getSummary());
    }

    @Test
    public void getNarcosEpisodeSummary() throws Exception {
        String firstEpSummary = "<p>A Chilean drugsproducer, the chemist Cockroach, brings his product to the Colombian smuggler Pablo Escobar. DEA-Agent Steve Murphy is fighting drugs in Bogota.</p>";
        String eigthEpSummary = "<p>A tragic mistake forces the government to change strategies to fight Pablo, but Pablo has bigger issues as there is danger from within his empire.</p>";
        assertEquals(firstEpSummary, narcosEpisode.getSummary());
        assertEquals(eigthEpSummary, narcosEpisodeArray[7].getSummary());
    }

    @Test
    public void setNarcosPremiereDate() throws Exception {
        narcos.setPremiereDate("2013-29-04");
        assertEquals("2013-29-04", narcos.getPremiereDate());
    }

    @Test
    public void getNarcosPremiereDate() throws Exception {
        assertEquals("2015-08-28", narcos.getPremiereDate());
    }

    @Test
    public void setNarcosEpisodeAirdate() throws Exception {
        narcos.setAirdate("2016-13-02");
        assertEquals("2016-13-02", narcos.getAirdate());
    }

    @Test
    public void getNarcosEpisodeAirdate() throws Exception {
        assertEquals("2015-08-28", narcosEpisodeArray[0].getAirdate());
    }

    @Test
    public void setNarcosEpisodeId() throws Exception {
        narcos.setEpisodeID(18410);
        assertEquals(18410, narcos.getId());
    }

    @Test
    public void getNarcosEpisodeId() throws Exception {
        assertEquals(208983, narcosEpisodeArray[6].getId());
    }

    @Test
    public void setNarcosGenres() throws Exception {
        List<String> genres = new LinkedList<>();
        genres.add("Drama");
        genres.add("Crime");
        narcos.setGenres(genres);
    }

    @Test
    public void getNarcosGenres() throws Exception {
        assertEquals(Arrays.asList("Drama", "Crime"), narcos.getGenres());
    }

    @Test
    public void getAllEpisodesInANarcosSeason() throws Exception {
        List<TVShow> narcosSeasonOne = new LinkedList<>();
        for (int k = 0; k < 10; k++) {
            narcosSeasonOne.add(narcosEpisodeArray[k]);
        }
        List<TVShow> narcosSeasonTwo = new LinkedList<>();
        for (int k = 10; k < 20; k++) {
            narcosSeasonTwo.add(narcosEpisodeArray[k]);
        }
        assertEquals(narcosSeasonOne, narcos.getAllEpisodesInASeason(narcosEpisodeList, 1));
        assertEquals(narcosSeasonTwo, narcos.getAllEpisodesInASeason(narcosEpisodeList, 2));
    }

    @Test
    public void nullListOfNarcosEpisodes() throws Exception {
        try {
            narcos.getAllEpisodesInASeason(null, 2);
            narcos.getAllEpisodesContainingKeywordInSummary(null, "La ");
            narcos.getMostRecentEpisodeAirdate(null);
            narcos.getTotalNumberOfEpisodes(null);
        } catch (NullPointerException e) {
            String expected = "List must contain episodes.";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void emptyListOfNarcosEpisodes() throws Exception {
        List<TVShow> emptyList = new LinkedList<>();
        try {
            narcos.getAllEpisodesInASeason(emptyList, 3);
            narcos.getAverageLengthofEpisode(emptyList);
            narcos.getOldestEpisodeTitle(emptyList);
            narcos.getMostRecentEpisodeAirdate(emptyList);
            narcos.getAllEpisodesInAYear(emptyList, "2016");
            narcos.getTotalNumberOfEpisodes(emptyList);
        } catch (IllegalArgumentException e) {
            String expected = "List must contain episodes.";
            assertEquals(expected, e.getMessage());
        }

    }

    @Test
    public void getAllNarcosEpisodesWithAGivenStringInTitle() throws Exception {
        List<TVShow> narcosLa = new LinkedList<>();
        for (int k = 0; k < 30; k++) {
            if (narcosEpisodeArray[k].getEpisodeName().contains("La ")) {
                narcosLa.add(narcosEpisodeArray[k]);
            }
        }
        assertEquals(narcosLa, narcos.getAllEpisodesContainingKeywordInTitle(narcosEpisodeList, "La "));
    }

    @Test
    public void nullKeywordNarcos() throws Exception {
        try {
            narcos.getAllEpisodesContainingKeywordInTitle(narcosEpisodeList, null);
            narcos.getAllEpisodesContainingKeywordInSummary(narcosEpisodeList, null);
        } catch (IllegalArgumentException | NullPointerException e) {
            String expected = "Keyword cannot be null.";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void getAllNarcosEpisodesInAGivenYear() throws Exception {
        List<TVShow> narcosYear2015 = new LinkedList<>();
        for (int k = 0; k < 30; k++) {
            if (narcosEpisodeArray[k].getAiryear().equals("2015")) {
                narcosYear2015.add(narcosEpisodeArray[k]);
            }
        }
        assertEquals(narcosYear2015, narcos.getAllEpisodesInAYear(narcosEpisodeList, "2015"));

    }

    @Test
    public void getAllNarcosEpisodesWithAGivenStringInSummary() throws Exception {
        List<TVShow> narcosPablo = new LinkedList<>();
        for (int k = 0; k < 30; k++) {
            if (narcosEpisodeArray[k].getSummary().contains("Pablo")) {
                narcosPablo.add(narcosEpisodeArray[k]);
            }
        }
        assertEquals(narcosPablo, narcos.getAllEpisodesContainingKeywordInSummary(narcosEpisodeList, "Pablo"));
    }

    @Test
    public void getTotalNumberOfNarcosEpisodes() throws Exception {
        assertEquals(30, narcos.getTotalNumberOfEpisodes(narcosEpisodeList));
        assertEquals(10, narcos.getTotalNumberOfEpisodes(TVShowData.parseBySeason(narcosEpisodeList, 1)));
    }

    @Test
    public void getMostRecentNarcosEpisodeAirdate() throws Exception {
        List<TVShow> laKeyword = narcos.getAllEpisodesContainingKeywordInTitle(narcosEpisodeList, "La ");
        assertEquals(narcosEpisodeArray[29].getAirdate(), narcos.getMostRecentEpisodeAirdate(narcosEpisodeList));
        assertEquals(narcosEpisodeArray[8].getAirdate(), narcos.getMostRecentEpisodeAirdate(laKeyword));
    }

    @Test
    public void getAverageLengthOfANarcosEpisode() throws Exception {
        List<TVShow> allEpisodesSeasonThree = narcos.getAllEpisodesInASeason(narcosEpisodeList, 3);
        assertEquals(60, narcos.getAverageLengthofEpisode(narcosEpisodeList));
        assertEquals(60, narcos.getAverageLengthofEpisode(allEpisodesSeasonThree));
    }

    @Test
    public void getOldestNarcosEpisodeName() throws Exception {
        List<TVShow> laKeyword = narcos.getAllEpisodesContainingKeywordInTitle(narcosEpisodeList, "La ");
        assertEquals("Descenso", narcos.getOldestEpisodeTitle(narcosEpisodeList));
        assertEquals("La Gran Mentira", narcos.getOldestEpisodeTitle(laKeyword));
    }

    @Test
    public void getTotalNumberOfWestworldEpisodes() throws Exception {
        assertEquals(20, westworld.getTotalNumberOfEpisodes(westworldEpisodeList));
    }

    @Test
    public void getWestworldSeason() throws Exception {
        assertEquals(2, westworldEpisodeArray[13].getSeason());
    }

    @Test
    public void getAllWestworldEpisodesWithAGivenStringInTitle() throws Exception {
        List<TVShow> westworldThe = new LinkedList<>();
        for (int k = 0; k < 20; k++) {
            if (westworldEpisodeArray[k].getEpisodeName().contains("The ")) {
                westworldThe.add(westworldEpisodeArray[k]);
            }
        }
        assertEquals(westworldThe, westworld.getAllEpisodesContainingKeywordInTitle(westworldEpisodeList, "The "));
    }

    @Test
    public void getMostRecentWestworldEpisodeAirdate() throws Exception {
        List<TVShow> theKeyword = westworld.getAllEpisodesContainingKeywordInTitle(westworldEpisodeList, "The ");

        assertEquals(westworldEpisodeArray[19].getAirdate(), westworld.getMostRecentEpisodeAirdate(theKeyword));
    }

    @Test
    public void nullKeywordWestworld() throws Exception {
        try {
            westworld.getAllEpisodesContainingKeywordInTitle(westworldEpisodeList, null);
            narcos.getAllEpisodesContainingKeywordInSummary(westworldEpisodeList, null);
        } catch (IllegalArgumentException | NullPointerException e) {
            String expected = "Keyword cannot be null.";
            assertEquals(expected, e.getMessage());
        }
    }

}
