<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>


    <xsl:template match="/database/table">
        public class <xsl:value-of select="@name"/>
        <xsl:text> implements java.io.Serializable { </xsl:text>

        <xsl:apply-templates select="column" mode="generateField"/>

        public <xsl:value-of select="@name"/>(<xsl:apply-templates select="column" mode="generateConstructorParam"/>
        <xsl:text>) { </xsl:text>
        <xsl:apply-templates select="column" mode="generateInitializers"/>
        }
        <xsl:apply-templates select="column" mode="generateGetterSetter"/>

        @Override
        public String toString(){
            return new com.google.gson.Gson().toJson(this);
        }

        }

    </xsl:template>


    <!--
    *****************************************************************
    ** Generate a private field declaration.
    **************************************************************-->
    <xsl:template match="column" mode="generateField">
        private <xsl:value-of select="@type"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="@name"/>;</xsl:template>


    <!--
     *****************************************************************
    ** Generate one of the constructor parameters.
    **************************************************************-->
    <xsl:template match="column" mode="generateConstructorParam">
        <xsl:text xml:space="preserve"/>
        <xsl:value-of select="@type"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="@name"/>
        <xsl:if test="position() != last( )">, </xsl:if>
    </xsl:template>


    <!--
    *****************************************************************
    ** Generate the initialization code inside of the constructor.
    **************************************************************-->
    <xsl:template match="column" mode="generateInitializers">
        this.<xsl:value-of select="@name"/><xsl:text> = </xsl:text><xsl:value-of select="@name"/><xsl:text>;</xsl:text>
    </xsl:template>


    <!--
    *****************************************************************
    ** Generate a "get" and a "set" methods for a property.
    **************************************************************-->
    <xsl:template match="column" mode="generateGetterSetter">
        public <xsl:value-of select="@type"/><xsl:text> get</xsl:text><xsl:value-of select="@name"/>() {
        return this.<xsl:value-of select="@name"/>;
        }

        public void set<xsl:value-of select="@name"/>(<xsl:value-of select="@type"/><xsl:text> </xsl:text><xsl:value-of select="@name"/>) {
        this.<xsl:value-of select="@name"/><xsl:text> = </xsl:text><xsl:value-of select="@name"/>;
        }
    </xsl:template>

<!--    &lt;!&ndash;-->
<!--    *****************************************************************-->
<!--    ** Generate JSON ToString method-->
<!--    **************************************************************&ndash;&gt;-->
<!--    <xsl:template match="table" mode="generateJsonToString">-->
<!--        @Override-->
<!--        public String toString(){-->
<!--            return new com.google.gson.Gson().toJson(this);-->
<!--        }-->
<!--    </xsl:template>-->


</xsl:stylesheet>