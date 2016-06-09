/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import de.reichel.bean.RepairEdit;
import de.reichel.bean.RepairNew;
import de.reichel.dao.AnlagenDAO;
import de.reichel.dao.ReparaturDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.util.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author alastair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/applicationContext.xml"})
public class ReparaturDAOImplTest {

    private ReparaturDAO reparaturDAO;
    private AnlagenDAO anlagenDAO;

    static {
        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

            InitialContext ic = new InitialContext();
            ic.createSubcontext("jdbc");

            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/ansent");
            ds.setZeroDateTimeBehavior("convertToNull");
            ds.setUser("root");
            ds.setPassword("root");

            ic.bind("jdbc/ReichelDS", ds);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    ApplicationContext context;

    @Before
    public void setUp() {
        reparaturDAO = context.getBean(ReparaturDAOImpl.class);
        anlagenDAO = context.getBean(AnlagenDAOImpl.class);
    }

    @Test @Ignore
    public void testGetAnlageArt() {
        String partDesc = reparaturDAO.getPartDescription(20061);
    }
    
    @Test @Ignore
    public void testGenerateAuftraege() {
        
//        String[] interneNrs = new String[]{"5840-B-194-PPK", "3800-K-??", "MAM-K-0738-RV", "291-B-141-RM", "3320-B-156-RM", "MAM-K-0732-RV", "3140-M-242-PPK", "3140-M-242-Cont", "MAM-K-0798-RV", "MAM-K-0762-RV", "MAM-K-0781-RV", "292-B-120-RM", "292-B-223-PPK", "GMZ-M-E-387", "617-B-168-PPK", "GMZ-M-1037", "GMZ-M-076", "GMZ-M-423", "MAM-K-0757-RV", "GMZ-M-E-384", "GMZ-M-059", "GMZ-M-209-F", "MAM-K-0859-RV", "MAM-K-0767-RV", "784-B-165-RM", "784-B-215-PPK", "MAM-K-0778-RV", "MAM-K-0779-RV", "446-B-269-PPK", "608-B-260-PPK", "358-B-372-PPK", "MAM-K-8088-RV", "178-B-074-PPK", "198-B-104-PPK", "198-B-105-RM", "329-B-124-RM", "KVP-K-1195/PR", "MAM-K-8087-RV", "555-B-PPK", "318-B-032-R", "Stü-K-0042-BP", "152-B-RM", "344-B-252-PPK", "344-B-298-RM", "Neu-K-0012 (CMS-K-0012)", "Neu-K-0013", "811-B-261-PPK", "MAM-K-0735-RV", "200-B-060-P", "797-B-061-PPK", "MTB-M-369", "678-B-247-PPK", "487-B-262-RM", "835-B-246-PPK", "835-B-324-RM", "ALE-K-0001", "ALE-K-0002", "370-B-123-RM", "370-B-122-PPK", "370-B-121-PPK", "HAG-K-0001", "MAM-K-8218-RV", "343-B-251-RM", "788-B-169-PPK", "788-B-170-RM", "793-M-201-R", "390-B-326-PPK", "700-B-328-PPK", "700-B-329-RM", "832-B-373-PPK", "832-B-374-RM", "MAM-K-0766-RV", "638-B-109-PPK", "385-B-304-PPK", "385-B-305-RM", "4040-B-210-PPK", "612-B-258-PPK", "612-B-259-RM", "562-B-334-PPK", "MAM-K-8203-BP(Getränkemarkt)", "MAM-K-0701-RV", "MAM-K-0727-RV", "BAH-M-124", "MAM-K-0826-RV", "453-B-RM", "453-B-335-PPK", "MAM-K-0452-RV", "MAM-K-0718-RV", "MAM-K-8745-CP", "MAM-K-8745-FP", "MAM-K-0793-RV", "MAM-K-0803-RV", "489-B-321-RM", "489-B-380-PPK", "MAM-K-0782-RV", "MAM-K-0674-RV", "MAM-K-0794-RV", "WEBE-M-350", "768-B-077-PPK", "728-B-234-PPK", "480-B-148-RM", "480-B-377-PPK", "3400-B-088-RM", "MAM-K-0837-RV", "MAM-K-0719-RV", "741-B-244-PPK", "741-B-245-RM", "MAM-K-0773-RV", "297-B-375-PPK", "297-B-376-RM", "MAM-K-0485-RV", "336-B-282-RM", "336-B-PPK", "447-B-319-RM", "447-B-313-PPK", "298-B-254-RM", "298-B-253-PPK", "166-B-238-PPK", "MAM-K-0759-RV", "MAM-K-0563-RV", "820-B-225-PPK", "820-B-243-RM", "720-B-221-PPK", "720-B-222-RM", "MAM-K-0434-RV", "MAM-K-8601-RV", "MAM-K-0764-RV", "MAM-K-0791-RV", "MAM-K-0800-RV", "MAM-K-0795-RV", "619-B-161-PPK", "840-B-318-RM", "840-B-317-PPK", "MAM-K-0425-RV", "MAM-K-0445-RV", "MAM-K-8680-RV", "MAM-K-0528-RV", "MAM-K-0458-RV1", "MAM-K-0458-RV2", "MAM-K-8230-RV", "890-B-379-PPK", "184-B-274-PPK", "184-B-273-RM", "6510-B-130-PPK", "426-M-238-R", "426-B-PPK", "MAM-K-0851-RV", "6290-K-RM", "MAM-K-0543-RV", "563-B-056-PPK", "MAM-K-8641-RV", "1490-M-204-RM", "MAM-K-0884-RV", "829-B-296-PPK", "829-B-297-RM", "MAM-K-0763-RV", "MAM-K-0663-RV", "MAM-K-0300-RV", "MAM-K-0300", "MAM-K-0610-RV", "762-B-231-PPK", "MAM-K-8921-BP", "3920-B-067-PPK", "MAM-K-8076-RV", "581-B-015-P", "581-B-007-R", "SHR-K-0001-BP", "681-B-090-PPK", "681-B-RM", "765-B-098-PPK", "214-B-001-R", "201-B-204-PPK", "419-B-163-RM", "MAM-K-8007-RV", "MAM-K-0863-RV", "770-B-168-PPK", "MAM-K-8589-RV", "EDE-M-317", "EDE-K-0002", "BERG-K-0008", "MAM-K-0830-RV", "202-B-142-RM", "202-B-220-PPK", "857-B-277-RM", "857-B-364-PPK", "858-B-PPK (612)", "WWK-M-180", "WWK-M-197", "OFD-M-093 (587)", "OFD-M-051", "OFD-M-PPK", "DB-K-0002", "DB-K-0003", "DB-K-0004", "DB-K-0005", "DB-K-0001", "DUS-K-0001", "SON-K-11000-PPK", "SON-K-11001", "DB-K-0002", "SON-K-00010", "SON-K-00011", "SON-K-11078", "SON-K-11079", "BHV-K-ST", "BMW-K-NL-0002", "SCW-K-001", "HTG-K-11015", "Stu-M-126", "PRE-M-391", "ES-K-001", "BMW-K-NL-0005", "BMW-K-NL-0003", "BMW-K-NL-0004", "WIT-K-077", "Pre-M-375", "PRE-M-416", "Pre-M-374-RM", "PRE-M-373", "PRE-M-301", "HAR-K-0001", "HAR-M-186", "PRE-M-379", "PRE-M-388", "PRE-M-389", "STE-M-400", "STE-M-401", "POL-K-0001", "HTG-K-10298", "NEP-K-0001-RM", "NEP-K-0002", "SON-K-11092", "BAY-M-383", "MTB-M-324 (351-K-064-P)", "WIT-K-071", "WIT-K-072", "Hei-M-361", "REM-K-2090", "REM-K-2091", "REM-K-2094", "REM-K-2095", "REM-K-2076", "REM-K-2092", "REM-K-2093", "RWE-K-1833", "5380-K-163-PPK", "5380-K-164-RM", "MAM-K-0650-RV", "786-A-RM", "SON-K-RP", "4900-A-RM", "4900-K-146-PPK", "781-A-PPK", "781-K-VW", "781-M-234-R", "5430-A-RM", "BERG-K-0009", "GOL-M-424", "872-B-308-PPK", "872-B-309-RM", "541-A-RM", "MAM-K-0632-RV", "4670-K-123-VW", "6905-F-BP1-Garantie", "LAM-K-0001", "4160-M-283 RM", "4160-M-318 PPK", "BMW-M-458", "BMW-M-459", "578-A-RM", "PRE-M-047", "MTB-M-460", "PRE-M-033", "PRE-M-155", "PRE-M-390", "587-A-RM", "587-K-182-P", "587-K-VW", "AVN-M-330 / 368-K-P", "SON-M-081", "BMW-K-NL-0001", "Erd-M-202", "OSH-M-191-RM", "OSH-M-118-PPK", "OSH-M-208-PPK", "6010-A-PPK", "BERG-K-0011", "449-A-PPK", "449-A-RM", "PRE-M-134-PPK", "Pre-M-247-RM", "DOR-M-271", "DOR-M-041", "SON-M-250", "DOR-M-065", "DOR-M-365", "MTB-M-142", "DOR-M-192", "DOR-M-022", "MSN-M-302", "MSN-M-310", "5510-A-PPK", "DOR-M-293-RM", "6650-K-207-RM", "6650-K-PPK SP", "507-A-RM", "507-B-212-PPK", "428-A-RM", "BAU-M-145", "BAU-M-412", "BAU-M-413", "7870-K-RM", "7870-K-PPK SP", "382-M-294-RM", "KRA-K-0001", "173-A-RM", "173-A-PPK", "363-A-RM", "363-A-PPK", "363-X-Folie", "241-K-PPK SP", "241-X-Folie", "WuW-K-0004", "771-B-201-PPK", "4830-X-BP-Garantie", "7720-B-RM", "7720-B-PPK", "7750-A-PPK", "4430-B-110-RM", "5440-K-PPK", "MAM-K-0775-RV", "BOS-K-0005", "BOS-K-0006", "BOS-K-0007", "BOS-K-0001", "BOS-K-0002", "BOS-K-0003", "BOS-K-0004", "BERG-K-0010", "6310-K-205-VW", "2570-K-PPK-Container", "MBLE-K-BP-20", "JOH-K-0001", "4560-K-113-Folie", "MAM-K-0772-RV", "MAM-K-0771-RV", "4540-B-092-RM", "GMZ-M-E-385", "4380-K-099-P", "203-B-113-PPK", "GMZ-M-1029", "287-B-112-PPK", "287-B-128-RM", "MAM-K-8783-RV", "5610-X-BP", "5610-M-236-PPK", "3680-B-114-RM"};
//        String[] interneNrs = new String[]{"ABG-A-18/1021", "ABG-A-20/1057","ABG-A-20/1072 HKV","ABG-A-18/1075","ABG-A-20/1103","444-A-12/1136 AzV","ABG-A-20/1201","ABG-A-12/1250 HKV","ABG-A-20/2074 HKV","ABG-A-20/1436","ABG-A-20/1439","ABG-A-KV/1464","ABG-A-12/1466 HKV","ABG-A-12/1952 (2002-A20)","ABG-A-20/1924 (2001-A30)","ABG-A-12/1483","ABG-A-13/1492","Pre-M-374-RM","MTB-M-324 (351-K-064-P)","397-K-084-PPK","4550-K-111-RM","504-K-158-PPK","583-K-PPK","SON-M-11029-P","242-K-RM","BAU-M-145","OSH-M-011","ROD-M-012","MTB-M-089","KiM-M-005","BAH-M-124","Stu-M-126","TVM-M-139","ABG-K-BP/0049","ABG-A-BP/1997-B02","ABG-A-20/2051","ABG-A-BP/2003-B15","7750-X-302-BP","ABG-A-BP/1998-B02","Neu-K-0044","Neu-K-0045","6440-X-BP","2050-X-009-BP","ABG-A-20/2058","Bay-K-0001-PPK","ABL-A-12/2013 HKV","KIM-K-1990","KHG-K-2002-01","KHG-K-2002-02","KHG-K-2002-03","PRE-M-134-PPK","6980-K-PPK","ABG-M-18/1610 HKV M168 (2003-10)","HAR-M-186","ABG-K-2020/PR","ABG-K-2043/PR","ABG-M-20/M280 HKV","ABG-M-20/1990 HKV (221)","745-K-PPK","WIT-M-252-R","581-B-015-P","318-B-032-R","478-K-PPK SP","HOS-K-0005","HOS-K-0006","ABG-A-20/3013","HTG-K-10298","4630-M-249 PPK","MAM-K-8383-RV","MAM-K-8007-RV","MAM-K-8653-RV","MAM-K-0402-RV","MAM-K-0610-RV","MAM-K-0634-RV","MAM-K-0650-RV","MAM-K-0701-RV","MAM-K-0763-RV","MAM-K-0766-RV","MAM-K-0781-RV","MAM-K-0786-RV","MAM-K-0787-RV","MAM-K-0800-RV","MAM-K-0826-RV","MAM-K-0863-RV","MAM-K-0434-RV","563-B-056-PPK","681-B-RM","Pre-M-247-RM","789-B-065-PPK","789-B-066-RM","SON-K-641","SON-K-674","SON-K-553","ABG-M-264","ABG-M-263","768-B-077-PPK","477-K-PPK SP","DOR-M-262","745-B-019-R","581-B-007-R","681-B-090-PPK","765-B-098-PPK","638-B-109-PPK","ABG-K-BP/0193","3680-B-114-RM","847-X-BP","583-X-BP","414-X-BP","504-X-BP","HAG-K-0001","HOS-K-0002","HOS-K-0003","657-B-062-PPK","477-X-BP","370-B-123-RM","370-B-122-PPK","370-B-121-PPK","292-B-120-RM","664-B-117-PPK","301-A-RM1","301-A-RM2","301-A-PPK1","301-A-PPK2","2050-A-RM","671-B-126-PPK","6510-B-130-PPK","478-X-BP","6980-X-BP","600-X-BP","242-X-BP","4550-X-BP","MAM-K-8214-RV","742-A-PPK","742-A-RM","WIS-K-0001","PRE-M-373","397-X-BP","755-X-BP-Garantie","PRE-M-301","ABG-M-20/1661-299 (2007-10)","5840-X-BP","Pre-M-375","3680-B-155-PPK","MCD-K-0001","MAM-K-8212-BP","MAM-K-8215-BP","393-B-162-PPK","MAM-K-8701-BP","MAM-K-8703-BP","SHR-K-0001-BP","419-B-163-RM","657-X-BP","619-B-161-PPK","EDS-K-RP1","ABG-M-20/1679-321 (2007-21)","HOS-K-0004","617-B-168-PPK","672-B-171-PPK","672-B-172-RM","6440-B-176-PPK","6440-B-177-RM","581-X-BP","477-B-182-RM","ABL-A-11078 HKV","ABL-A-20/1960 HKV","259-X-BP","617-X-BP","720-B-221-PPK","720-B-222-RM","292-B-223-PPK","4630-X-BP","363-X-Folie","301-X-BP","672-X-Folie","4630-A-RM","ABG-A-20/1721 (KL376)","MAM-K-8101-BP2","363-A-RM","SCW-K-001","ABL-A-12/1725 (2009-07)","343-B-251-RM","742-X-BP","PRE-M-388","487-B-262-RM","846-B-263-PPK","847-A-PPK","847-A-RM","844-B-264-PPK","844-B-265-RM","845-B-267-RM","845-B-266-PPK","7660-B-280-PPK","766-B-281-RM","MTB-M-369","766-X-BP","444-B-PPK","PRE-M-399","STE-M-400","STE-M-401","SON-M-11093","BAU-M-412","BAU-M-413","ABG-A-KV/1740","ABG-A-20/1742 HKV (2010-11)","ABG-A-20/1743 HKV (2010-12)","882-B-331-PPK","882-B-332-RM","146-B-RM","872-X-BP","507-X-BP-Garantie","562-B-334-PPK","HOS-K-0001","PRE-M-416","285-B-337-RM","371-B-338-RM","554-B-339-RM","734-B-340-RM","423-B-341-RM","583-B-336-RM","287-X-BP-Garantie","6905-F-BP1-Garantie","242-A-PPK","2050-A-PPK","173-X-BP-Garantie","5510-A-PPK","363-A-PPK","BMW-K-Lastenhebemagnet","BMW-K-Zweischalengreifer","BMW-K-Fasslifter","BMW-K-Motorgreifer","BMW-K-Fassheber","BMW-K-Kettengehänge","BMW-K-Fasspressenhaken","BMW-K-Hochdruckreiniger","191-X-BP-Garantie","296-X-BP-Garantie","186-X-BP-Garantie","1323-X-BP-Garantie","7750-A-PPK","832-B-373-PPK","832-B-374-RM","890-B-379-PPK","ABG-K-0239-08/ST","ABG-K-0239-09/Beh.","MAM-K-8203-BP(Getränkemarkt)","ABG-K-20/1771 HKV (2012-17)","ABG-K-20/1772 HKV (2012-18)","PRE-M-391","WIT-K-077","HAR-K-0001","ABG-K-20/1774 (2012-20)","ABG-K-20/1773 (2012-19)","364-K-PPK SP","755-K-PPK","755-K-RM","472-X-BP","647-K-PPK","647-X-BP","647-K-RM","565-K-PPK SP","565-K-RM","364-X-BP","ABG-K-2012-01/BP","243-X-BP","398-X-BP","745-X-BP","6670-X-BP-Garantie","670-X-BP-Garantie","139-X-BP-Garantie","144-X-BP-Garantie","134-X-BP-Garantie","235-X-BP-Garantie","4830-X-BP-Garantie","539-X-BP-Garantie","260-X-BP","4320-X-BP-Garantie","280-X-BP-Garantie","5660-X-BP-Garantie","865-X-BP-Garantie","8780-X-BP-Garantie","177-X-BP-Garantie","874-X-BP-Garantie","841-X-BP-Garantie","8730-X-BP-Garantie","260-X-BP-Garantie","ABG-A-PV/2012-01","ABG-A-PV/2012-02","6510-X-BP","Erd-M-202"};
        String[] interneNrs = new String[]{"Rada-M-333",
"Rada-M-048",
"OSH-M-011",
"HGI-M-006",
"ROD-M-012",
"BEE-M-016",
"BEE-M-026",
"BEE-M-013",
"MTB-M-089",
"ABG-M-20/1715 (090)",
"ABG-M-20/1716 (091)",
"4640-M-109 RM",
"ABG-M-PS/0128",
"TVM-M-139",
"SON-M-11029-P",
"Rada-M-163-P",
"Neu-K-0044",
"Neu-K-0045",
"OSH-M-190-RM",
"Bay-K-0001-PPK",
"KIM-K-1990",
"KHG-K-2002-01",
"KHG-K-2002-02",
"KHG-K-2002-03",
"DER-M-205",
"Rada-M-183",
"BEE-M-219",
"ABG-M-20/1990 HKV (221)",
"ABG-M-20/1635 (025)",
"150-M-227-PPK",
"WIT-M-252-R",
"ABG-M-20/1621-232 (2005-01)",
"4630-M-249 PPK",
"MAM-K-8383-RV",
"MAM-K-8653-RV",
"MAM-K-8781-RV",
"MAM-K-0402-RV",
"MAM-K-0634-RV",
"MAM-K-0786-RV",
"MAM-K-0787-RV",
"MAM-K-0824-RV",
"151-M-259 PPK",
"SON-K-641",
"SON-K-674",
"SON-K-553",
"ABG-M-264",
"ABG-M-263",
"DOR-M-262",
"541-M-PPK (M-268)",
"MAM-K-8214-RV",
"WIS-K-0001",
"MCD-K-0001",
"MAM-K-8212-BP",
"MAM-K-8215-BP",
"MAM-K-8701-BP",
"MAM-K-8703-BP",
"EDS-K-RP1",
"BEE-M-335",
"BEE-M-360",
"MAM-K-8101-BP2",
"BEE-M-346",
"PRE-M-399",
"SON-M-11093",
"BMW-M-421",
"BEE-M-436",
"Rada-M-429",
"Rada-M-430",
"BEE-M-420",
"POS-K-0001",
"BMW-K-Lastenhebemagnet",
"BMW-K-Zweischalengreifer",
"BMW-K-Fasslifter",
"BMW-K-Motorgreifer",
"BMW-K-Fassheber",
"BMW-K-Kettengehänge",
"BMW-K-Fasspressenhaken",
"BMW-K-Hochdruckreiniger",
"Rada-M-441",
"BEE-M-445",
"BMW-M-437"};
        
        for (String interneNr: interneNrs) {
            System.out.println("Generating Auftrag for " + interneNr);            
            Object[] anlagen = anlagenDAO.getAnlagen(interneNr);
            Integer idAnlagen = ((Anlagen)anlagen[0]).getIdAnlagen();
            RepairEdit backingBean = new RepairEdit();
            backingBean.setIdAnlagen(idAnlagen);
            backingBean.setRepairDate(new Date());
            backingBean.setWorkDescription("Wartung und BGR 186 Prüfung durchführen");
            generateAuftrag(backingBean);            
            System.out.println("Generated Auftrag for " + interneNr);
        }
    }
    
    public void generateAuftrag(RepairEdit repairEdit) {
        reparaturDAO.addRepair(repairEdit);
        try {
            byte[] invoiceBytes = reparaturDAO.generateAuftrag(repairEdit);
            File pdf = new File(Utils.getAuftragDirPath() + File.separator + "batch" + File.separator + "reparatur-auftrag-" + Utils.fileDateFormat.format(repairEdit.getRepairDate()) + "-" + repairEdit.getIdRepair() + ".pdf");
            //File pdf = new File(Utils.getAuftragDirPath() + File.separator + "batch" + File.separator + "combined.pdf");
            OutputStream os = new FileOutputStream(pdf);
            os.write(invoiceBytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }        
}
