<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:template match="/">
		<html>
			<head>
				<title>
						Camera Search Pretty Print
					</title>
				<style type="text/css">
					body
					{
						line-height: 1.6em;
					}
					
					#hor-zebra
					{
						font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
						font-size: 12px;
						margin: 45px;
						width: 480px;
						text-align: left;
						border-collapse: collapse;
					}
					#hor-zebra th
					{
						font-size: 14px;
						font-weight: normal;
						padding: 10px 8px;
						color: #039;
					}				
					#hor-zebra tr
					{
						vertical-align: top; 
					}
					#hor-zebra td
					{
						padding: 8px;
						color: #669;
					}
					#hor-zebra .odd
					{
						background: #e8edff; 
					}
					#hor-zebra .separator
					{
						font-size: 14px;
						font-weight: normal;
						padding: 10px 8px;
						color: #039;
						text-align: center;
					}				
					legend
					{
						font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
						font-size: 32px;
					}
				</style>
			</head>
			<body>
				<fieldset>
					<xsl:apply-templates/>
				</fieldset>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="Name">
		<legend>
			<xsl:value-of select="."/>
		</legend>
	</xsl:template>
	<xsl:template match="Cameras">
		<xsl:for-each select="Camera">
			<table id="hor-zebra">
				<tbody>
					<tr class="odd">
						<th>Camera Name</th>
						<th>
							<xsl:value-of select="Model"/>
						</th>
					</tr>
					<tr>
						<td>Date</td>
						<td>
							<xsl:value-of select="Date"/>
						</td>
					</tr>
					<tr class="odd">
						<td colspan="2">
							<a href="{DepthReviewUrl}">
								<img alt="Click for indepth Review" src="{PictureUrl}"/>
							</a>
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td>
							<xsl:value-of select="Description"/>
						</td>
					</tr>
					<tr class="odd">
						<td colspan="2" class="separator">Camera Details</td>
					</tr>
					<tr>
						<td>ImageRatio</td>
						<td>
							<xsl:value-of select="ImageRatio"/>
						</td>
					</tr>
					<tr class="odd">
						<td>EffectivePixels</td>
						<td>
							<xsl:value-of select="EffectivePixels"/>
						</td>
					</tr>
					<tr>
						<td>SensorSize</td>
						<td>
							<xsl:value-of select="SensorSize"/>
						</td>
					</tr>
					<tr class="odd">
						<td>IsoRating</td>
						<td>
							<xsl:value-of select="IsoRating"/>
						</td>
					</tr>
					<tr>
						<td>Shutter Speed</td>
						<td>
							<xsl:value-of select="MinShutterSpeed"/>
								 - 
								<xsl:value-of select="MaxShutterSpeed"/>
						</td>
					</tr>
					<tr class="odd">
						<td>Max Resolution</td>
						<td>
							<ul>
								<xsl:for-each select="MaxResolutions/MaxResolution">
									<li>
										<xsl:value-of select="Horiz"/>x<xsl:value-of select="Vert"/>
									</li>
								</xsl:for-each>
							</ul>
						</td>
					</tr>
					<tr>
						<td>Other Resolutions</td>
						<td>
							<ul>
								<xsl:for-each select="LowerResolutions/LowerResolution">
									<li>
										<xsl:value-of select="Horiz"/>x<xsl:value-of select="Vert"/>
									</li>
								</xsl:for-each>
							</ul>
						</td>
					</tr>
					<tr class="odd">
						<td>ISO Ratings</td>
						<td>
							<ul>
								<xsl:for-each select="IsoRatings/IsoRating">
									<li>
										<xsl:value-of select="."/>
									</li>
								</xsl:for-each>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
			<br/>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
