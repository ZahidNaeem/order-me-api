CREATE OR REPLACE FUNCTION GET_SUBJECT_HIERARCHY (P_SUBJECT_ID NUMBER)
    RETURN VARCHAR2
IS
    V_VALUE   VARCHAR2 (4000);
BEGIN
        SELECT LISTAGG (S.SUBJECT_ID, ',') WITHIN GROUP (ORDER BY S.SUBJECT_ID)
          INTO V_VALUE
          FROM SUBJECT S
    START WITH S.SUBJECT_ID = P_SUBJECT_ID
    CONNECT BY PRIOR S.SUBJECT_ID = S.PARENT_SUBJECT_ID;

    RETURN V_VALUE;
END;