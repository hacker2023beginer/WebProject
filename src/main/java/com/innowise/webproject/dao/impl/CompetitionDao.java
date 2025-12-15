package com.innowise.webproject.dao.impl;

import com.innowise.webproject.connectionpool.PostgresConnectionPool;
import com.innowise.webproject.entity.impl.Competition;
import com.innowise.webproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompetitionDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_COMPETITION = "INSERT INTO competitions (team_home, team_away, date, status, result, odds_home_win, odds_draw, odds_away_win) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM competitions WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM competitions";
    private static final String UPDATE_COMPETITION = "UPDATE competitions SET team_home=?, team_away=?, date=?, status=?, result=?, odds_home_win=?, odds_draw=?, odds_away_win=? WHERE id=?";
    private static final String DELETE_COMPETITION = "DELETE FROM competitions WHERE id=?";
    private static final String UPDATE_ODDS = "UPDATE competitions SET odds_home_win=?, odds_draw=?, odds_away_win=? WHERE id=?";
    private static final String UPDATE_RESULT = "UPDATE competitions SET result=?, status='FINISHED' WHERE id=?";

    public void addCompetition(Competition competition) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMPETITION)) {
            preparedStatement.setString(1, competition.getTeamHome());
            preparedStatement.setString(2, competition.getTeamAway());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(competition.getDate()));
            preparedStatement.setString(4, competition.getStatus());
            preparedStatement.setString(5, competition.getResult());
            preparedStatement.setDouble(6, competition.getOddsHomeWin());
            preparedStatement.setDouble(7, competition.getOddsDraw());
            preparedStatement.setDouble(8, competition.getOddsAwayWin());
            preparedStatement.executeUpdate();
            logger.info("Competition added: {} vs {}", competition.getTeamHome(), competition.getTeamAway());
        } catch (SQLException e) {
            throw new DaoException("Error adding competition", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public Optional<Competition> getCompetitionById(int id) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Competition competition = mapCompetition(resultSet);
                return Optional.of(competition);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Error retrieving competition by ID", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public List<Competition> getAllCompetitions() throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        List<Competition> competitions = new ArrayList<>();
        try (Statement connectionStatement = connection.createStatement()) {
            ResultSet resultSet = connectionStatement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                competitions.add(mapCompetition(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving competitions", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
        return competitions;
    }

    public void updateCompetition(Competition competition) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMPETITION)) {
            preparedStatement.setString(1, competition.getTeamHome());
            preparedStatement.setString(2, competition.getTeamAway());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(competition.getDate()));
            preparedStatement.setString(4, competition.getStatus());
            preparedStatement.setString(5, competition.getResult());
            preparedStatement.setDouble(6, competition.getOddsHomeWin());
            preparedStatement.setDouble(7, competition.getOddsDraw());
            preparedStatement.setDouble(8, competition.getOddsAwayWin());
            preparedStatement.setInt(9, competition.getId());
            preparedStatement.executeUpdate();
            logger.info("Competition updated: {}", competition.getId());
        } catch (SQLException e) {
            throw new DaoException("Error updating competition", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public void deleteCompetition(int id) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMPETITION)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.info("Competition deleted: {}", id);
        } catch (SQLException e) {
            throw new DaoException("Error deleting competition", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public void updateOdds(int id, double homeWin, double draw, double awayWin) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ODDS)) {
            preparedStatement.setDouble(1, homeWin);
            preparedStatement.setDouble(2, draw);
            preparedStatement.setDouble(3, awayWin);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            logger.info("Odds updated for competition {}", id);
        } catch (SQLException e) {
            throw new DaoException("Error updating odds", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public void setResult(int id, String result) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_RESULT)) {
            ps.setString(1, result);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Result set for competition {}: {}", id, result);
        } catch (SQLException e) {
            throw new DaoException("Error setting result", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private Competition mapCompetition(ResultSet rs) throws SQLException {
        Competition competition = new Competition();
        competition.setId(rs.getInt("id"));
        competition.setTeamHome(rs.getString("team_home"));
        competition.setTeamAway(rs.getString("team_away"));
        competition.setDate(rs.getTimestamp("date").toLocalDateTime());
        competition.setStatus(rs.getString("status"));
        competition.setResult(rs.getString("result"));
        competition.setOddsHomeWin(rs.getDouble("odds_home_win"));
        competition.setOddsDraw(rs.getDouble("odds_draw"));
        competition.setOddsAwayWin(rs.getDouble("odds_away_win"));
        return competition;
    }
}
